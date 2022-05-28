package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Management;

public class ManagementConverter {
    public static Management toModel(ManagementView mv) {
        return new Management(
                mv.getId(),
                mv.getProgess(),
                EmployeeConverter.toModel(mv.getEmployee()),
                mv.getProjectnumber(),
                mv.getClient(),
                mv.getQuantity(),
                mv.getRequester(),
                mv.getContent(),
                mv.getDeadline(),
                mv.getPredict(),
                mv.getHelp());

    }

    public static ManagementView toView(Management m) {
        if(m == null) {
            return null;
        }

        return new ManagementView(
                m.getId(),
                m.getProgess(),
                EmployeeConverter.toView(m.getEmployee()),
                m.getProjectnumber(),
                m.getClient(),
                m.getQuantity(),
                m.getReuqester(),
                m.getContent(),
                m.getDeadline(),
                m.getPredict(),
                m.getHelp());
    }

    public static List<ManagementView> toViewList(List<Management> list) {
        List<ManagementView> evs = new ArrayList<>();

        for(Management m : list) {
            evs.add(toView(m));
        }
        return evs;
    }

    public static void copyViewToModel(Management m, ManagementView mv) {
        m.setId(mv.getId());
        m.setProgess(mv.getProgess());
        m.setEmployee(EmployeeConverter.toModel(mv.getEmployee()));
        m.setProjectnumber(mv.getProjectnumber());
        m.setClient(mv.getClient());
        m.setQuantity(mv.getQuantity());
        m.setReuqester(mv.getRequester());
        m.setContent(mv.getContent());
        m.setDeadline(mv.getDeadline());
        m.setPredict(mv.getPredict());
        m.setHelp(mv.getHelp());
    }
}
