package services;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ManagementConverter;
import actions.views.ManagementView;
import constants.JpaConst;
import models.Management;
import models.validators.ManagementValidator;

public class ManagementService extends ServiceBase {

    public List<ManagementView> getMinePerPage(EmployeeView employee, int page) {
        List<Management> managements = em.createNamedQuery(JpaConst.Q_MAN_GET_ALL_MINE,Management.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ManagementConverter.toViewList(managements);
    }

    public long countAllMine(EmployeeView employee) {
        long count = (long)em.createNamedQuery(JpaConst.Q_MAN_COUNT_ALL_MINE,Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }
    public List<ManagementView> getAllPerPage(int page) {
        List<Management> managements = em.createNamedQuery(JpaConst.Q_MAN_GET_ALL,Management.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ManagementConverter.toViewList(managements);
    }

    public long countAll() {
        long managements_count = (long) em.createNamedQuery(JpaConst.Q_MAN_COUNT,Long.class)
                .getSingleResult();
        return managements_count;
    }

    public ManagementView findOne(int id) {
        return ManagementConverter.toView(findOneInternal(id));
    }

    public List<String> create(ManagementView mv) {
        List<String> errors = ManagementValidator.validate(mv);
        if(errors.size() == 0) {
            createInternal(mv);
        }
        return errors;
    }

    public List<String> update(ManagementView mv) {
        List<String> errors = ManagementValidator.validate(mv);

        if(errors.size() == 0) {
            updateInternal(mv);

        }

        return errors;
    }

    private Management findOneInternal(int id) {
        return em.find(Management.class,id);
    }

    private void createInternal(ManagementView mv) {
        em.getTransaction().begin();
        em.persist(ManagementConverter.toModel(mv));
        em.getTransaction().commit();
    }

    private void updateInternal(ManagementView mv) {
        em.getTransaction().begin();
        Management m = findOneInternal(mv.getId());
        ManagementConverter.copyViewToModel(m, mv);
        em.getTransaction().commit();
    }
}
