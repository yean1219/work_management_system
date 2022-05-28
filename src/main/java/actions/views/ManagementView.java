package actions.views;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ManagementView {
    private Integer id;

    private String progess;

    private EmployeeView employee;

    private String projectnumber;

    private String client;

    private Integer quantity;

    private String requester;

    private String content;

    private Timestamp deadline;

    private Timestamp predict;

    private Boolean help;

}
