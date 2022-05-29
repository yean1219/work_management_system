package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ManagementView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
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

}
