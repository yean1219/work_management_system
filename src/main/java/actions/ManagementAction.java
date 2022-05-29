package actions;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ManagementView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ManagementService;

public class ManagementAction extends ActionBase {

    private ManagementService service;

    @Override
    public void process() throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        service = new ManagementService();

        invoke();
        service.close();
    }

    public void index() throws ServletException,IOException {
        int page = getPage();
        List<ManagementView> managements = service.getAllPerPage(page);

        long managementsCount = service.countAll();

        putRequestScope(AttributeConst.MANAGEMENTS,managements);
        putRequestScope(AttributeConst.MAN_COUNT, managementsCount); //全ての日報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_MAN_INDEX);
    }
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        ManagementView mv = new ManagementView();
        putRequestScope(AttributeConst.MANAGEMENT, mv); //日付のみ設定済みの作業インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_MAN_NEW);

    }
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            // 納期
            Timestamp  deatLine = toTimestamp(getRequestParam(AttributeConst.MAN_DEADLINE));

            // 予測完了時間
            Timestamp  predict = toTimestamp(getRequestParam(AttributeConst.MAN_PREDICT));


            //パラメータの値をもとに日報情報のインスタンスを作成する
            ManagementView mv = new ManagementView(
                    null,
                    getRequestParam(AttributeConst.MAN_PROGESS),
                    ev, //ログインしている従業員を、日報作成者として登録する
                    getRequestParam(AttributeConst.MAN_PROJECTNUMBER),
                    getRequestParam(AttributeConst.MAN_CLIENT),
                    getRequestParam(AttributeConst.MAN_QUANTITY),
                    getRequestParam(AttributeConst.MAN_REQUESTER),
                    getRequestParam(AttributeConst.MAN_CONTENT),
                    deatLine,
                    predict,
                    getRequestParam(AttributeConst.MAN_HELP));

            //日報情報登録
            List<String> errors = service.create(mv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.MANAGEMENT, mv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_MAN_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_MAN, ForwardConst.CMD_INDEX);
            }
        }
    }

    private Timestamp toTimestamp(String str) {
        Timestamp timeStamp = null;
        if (str != null && !str.equals("")) {
            str = str.replace("T", " ");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date;
            try {
                date = sdf.parse(str);
                timeStamp = new Timestamp(date.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeStamp;
    }
    public void show() throws ServletException, IOException {

        //idを条件に日報データを取得する
        ManagementView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MAN_ID)));

        if (mv == null) {
            //該当の日報データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.MANAGEMENT, mv); //取得した日報データ

            //詳細画面を表示
            forward(ForwardConst.FW_MAN_SHOW);
        }
    }
    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        ManagementView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MAN_ID)));

        if (mv == null) {
            //該当の日報データが存在しない、または
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.MANAGEMENT, mv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_MAN_EDIT);
        }

    }
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            ManagementView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MAN_ID)));

            // 納期
            Timestamp  deatLine = toTimestamp(getRequestParam(AttributeConst.MAN_DEADLINE));

            // 予測完了時間
            Timestamp  predict = toTimestamp(getRequestParam(AttributeConst.MAN_PREDICT));

            //入力された日報内容を設定する
            mv.setProgess(getRequestParam(AttributeConst.MAN_PROGESS));
            mv.setProjectnumber(getRequestParam(AttributeConst.MAN_PROJECTNUMBER));
            mv.setClient(getRequestParam(AttributeConst.MAN_CLIENT));
            mv.setQuantity(getRequestParam(AttributeConst.MAN_QUANTITY));
            mv.setRequester( getRequestParam(AttributeConst.MAN_REQUESTER));
            mv.setContent(getRequestParam(AttributeConst.MAN_CONTENT));
            mv.setDeadline(deatLine);
            mv.setPredict(predict);
            mv.setHelp(getRequestParam(AttributeConst.MAN_HELP));

            //日報データを更新する
            List<String> errors = service.update(mv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.MANAGEMENT, mv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_MAN_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_MAN, ForwardConst.CMD_INDEX);

            }
        }
    }

}
