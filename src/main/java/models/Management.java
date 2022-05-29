package models;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 作業データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_MAN)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_MAN_GET_ALL,
            query = JpaConst.Q_MAN_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_MAN_COUNT,
            query = JpaConst.Q_MAN_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_MAN_GET_ALL_MINE,
            query = JpaConst.Q_MAN_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_MAN_COUNT_ALL_MINE,
            query = JpaConst.Q_MAN_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Management {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.MAN_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = JpaConst.MAN_COL_PROGESS, nullable = false)
    private String progess;

    /**
     * 日報を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.MAN_COL_EMP_NAME, nullable = false)
    private Employee employee;



    @JoinColumn(name = JpaConst.MAN_COL_PRO_NUM, nullable = false)
    private String projectnumber;

    @JoinColumn(name = JpaConst.MAN_COL_CLI, nullable = false)
    private String client;

    @JoinColumn(name = JpaConst.MAN_COL_QUA, nullable = false)
    private String quantity;

    @JoinColumn(name = JpaConst.MAN_COL_REQ, nullable = false)
    private String reuqester;

    @Lob
    @JoinColumn(name = JpaConst.MAN_COL_CONTENT, nullable = false)
    private String content;

    @JoinColumn(name = JpaConst.MAN_COL_DEADLINE, nullable = false)
    private Timestamp deadline;

    @JoinColumn(name = JpaConst.MAN_COL_PREDICT, nullable = false)
    private Timestamp predict;

    @Column(name = JpaConst.MAN_COL_HELP, nullable = false)
    private String help;

    /**
     * 日報のタイトル

    @Column(name = JpaConst.MAN_COL_TITLE, length = 255, nullable = false)
    private String title;
    */
    /**
     * 登録日時
    @Column(name = JpaConst.MAN_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;


    @Column(name = JpaConst.MAN_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
    */
}