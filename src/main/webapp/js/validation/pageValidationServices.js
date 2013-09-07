/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 24.08.13
 * Time: 7:25
 * To change this template use File | Settings | File Templates.
 */
//

$(document).ready(function () {

    $('#service-details-block').validate({
        rules: {
            inputServiceNaming: {
                minlength: 5,
                required: true
            },
            inputServiceCost: {
                required: true,
                minlength: 5
            },
            inputServiceData: {
                minlength: 5,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.controls').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.controls').removeClass('error').addClass('success');
        }

    });
    $(document).oneTime("1s", function () {
        $("#service-list-block").tablesorter();
        $("#service-list-block").oneSimpleTablePagination({rowsPerPage: 5});
        $("#datetimepicker").datetimepicker({
            pickTime: false
        });
    })
});