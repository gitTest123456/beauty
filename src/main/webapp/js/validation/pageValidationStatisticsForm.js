/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 08.09.13
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {

    $('#datetimepicker1').datetimepicker({
        pickTime: false
    });

    $("#datetimepicker2").datetimepicker({
        pickDate: false
    });

    $("#statistic-list-block").tablesorter();
    $("#statistic-list-block").oneSimpleTablePagination({rowsPerPage: 5});
});
