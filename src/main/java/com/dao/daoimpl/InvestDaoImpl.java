package com.dao.daoimpl;

import com.dao.InvestDao;
import com.model.InvestEntity;
import com.report.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 10.08.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Service
public class InvestDaoImpl implements InvestDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Autowired
    DataSource dataSource;

    private static Logger log = Logger.getLogger(InvestDaoImpl.class.getName());

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<InvestEntity> getAllInvest() {
        List<InvestEntity> invest = sessionFactory.getCurrentSession().createQuery("from com.model.InvestEntity").list();
        return invest;
    }


    public void deleteInvest(InvestEntity investEntity) {
        sessionFactory.getCurrentSession().delete(investEntity);
    }


    public void updateInvest(InvestEntity investEntity) {
        sessionFactory.getCurrentSession().merge(investEntity);
    }

    public void addInvest(InvestEntity invest) {
        sessionFactory.getCurrentSession().merge(invest);
    }

    public void printInvestReport() {
        String query = "select month(date_req) as dateReq,sum(arenda)+sum(common_req_env)+sum(salary) as common, " +
                " sum(arenda)as arnd,sum(common_req_env) as env, sum(salary) as emplSal" +
                " from invest, employer" +
                " group by month(date_req)";
        try {
            JasperReportBuilder reportBuilder = report().setTemplate(Templates.reportTemplate)
                    .columns(
                            col.column("Общая сумма", "common", type.stringType()),
                            col.column("Аренда", "arnd", type.stringType()),
                            col.column("Оборудование", "env", type.stringType()),
                            col.column("Зарплата сотрудников", "emplSal", type.stringType()),
                            col.column("Месяц", "dateReq", type.stringType()))
                    .title(Templates.createTitleComponent("Расход за месяц"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(query, dataSource.getConnection());
            JasperPrint reportPrint = reportBuilder.toJasperPrint();
            JasperViewer reportViewer = new JasperViewer(reportPrint, false);
            reportViewer.setTitle("Отчет по расходам за весь период времени");
            reportViewer.setVisible(true);
        } catch (DRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printReport() {
        String query = "select serv.naming, concat_ws(',',e.first_name, e.sur_name, e.last_name) empData, " +
                " sum(serv.cost - cl.discount/100*serv.cost) commonCost         " +
                " from statistic ststc" +
                " left join service serv on(ststc.service_id = serv.service_id)" +
                " left join employer e on(ststc.employer_id = e.employer_id)" +
                " left join clients cl on(ststc.client_id = cl.client_id)"+
                " group by empData, serv.naming   ";
        try {
            JasperReportBuilder reportBuilder = report().setTemplate(Templates.reportTemplate)
                    .columns(
                            col.column("Item", "naming", type.stringType()),
                            col.column("Сотрудник", "empData", type.stringType()),
                            col.column("Доход за месяц", "commonCost", type.stringType())
                    )
                    .title(Templates.createTitleComponent("Помесячный доход"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(query, dataSource.getConnection());
            JasperPrint reportPrint = reportBuilder.toJasperPrint();
            JasperViewer reportViewer = new JasperViewer(reportPrint, false);
            reportViewer.setTitle("Отчет");
            reportViewer.setVisible(true);
        } catch (DRException e) {

        } catch (SQLException e) {
        }
    }


}
