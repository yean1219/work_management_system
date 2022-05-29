package constants;

public interface JpaConst {
  //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "work_management_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_MAN = "managements"; //テーブル名
    //日報テーブルカラム
    String MAN_COL_ID = "id"; //id
    //String MAN_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String MAN_COL_PROGESS = "progess";
    String MAN_COL_EMP_NAME = "employee_name";
    String MAN_COL_PRO_NUM = "projectnumber";
    String MAN_COL_CLI = "client";
    String MAN_COL_QUA = "quantity";
    String MAN_COL_REQ = "requester";
    String MAN_COL_CONTENT = "content"; //日報の内容
    String MAN_COL_DEADLINE = "deadline";
    String MAN_COL_PREDICT = "predict";
    String MAN_COL_HELP = "help";
    //String MAN_COL_MAN_DATE = "management_date"; //いつの日報かを示す日付
    //String MAN_COL_TITLE = "title"; //日報のタイトル

    //String MAN_COL_CREATED_AT = "created_at"; //登録日時
    //String MAN_COL_UPDATED_AT = "updated_at"; //更新日時


    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_MAN = "management"; //作業

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全ての日報をidの降順に取得する
    String Q_MAN_GET_ALL = ENTITY_MAN + ".getAll";
    String Q_MAN_GET_ALL_DEF = "SELECT m FROM Management AS m ORDER BY m.id DESC";
    //全ての日報の件数を取得する
    String Q_MAN_COUNT = ENTITY_MAN + ".count";
    String Q_MAN_COUNT_DEF = "SELECT COUNT(m) FROM Management AS m";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_MAN_GET_ALL_MINE = ENTITY_MAN + ".getAllMine";
    String Q_MAN_GET_ALL_MINE_DEF = "SELECT m FROM Management AS m WHERE m.progess in('未', '進行中') m.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY m.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_MAN_COUNT_ALL_MINE = ENTITY_MAN + ".countAllMine";
    String Q_MAN_COUNT_ALL_MINE_DEF = "SELECT COUNT(m) FROM Management AS m WHERE m.progess in('未', '進行中') m.employee = :" + JPQL_PARM_EMPLOYEE;

}
