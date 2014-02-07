/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 24.08.13
 * Time: 7:25
 * To change this template use File | Settings | File Templates.
 */


$(document).ready(function () {

    $('#client-details-block').validate({
        rules: {
            inputName: {
                minlength: 5,
                required: true
            },
            inputSurName: {
                required: true,
                minlength: 5
            },
            inputLastName: {
                minlength: 5,
                required: true
            },
            inputPhone: {
                minlength: 5,
                required: true
            },
            name: {
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
        $("#client-list-block").tablesorter();
        $("#client-list-block").oneSimpleTablePagination({rowsPerPage: 5});
    })


});

function doSearch() {
        var searchText = document.getElementById('searchTerm').value;
        var targetTable = document.getElementById('client-list-block');
        var targetTableColCount;

        //Loop through table rows
        for (var rowIndex = 0; rowIndex < targetTable.rows.length; rowIndex++) {
            var rowData = '';

            //Get column count from header row
            if (rowIndex == 0) {
               targetTableColCount = targetTable.rows.item(rowIndex).cells.length;
               continue; //do not execute further code for header row.
            }

            //Process data rows. (rowIndex >= 1)
            for (var colIndex = 0; colIndex < targetTableColCount; colIndex++) {
                rowData += targetTable.rows.item(rowIndex).cells.item(colIndex).textContent;
            }

            //If search term is not found in row data
            //then hide the row, else show
            if (rowData.indexOf(searchText) == -1)
                targetTable.rows.item(rowIndex).style.display = 'none';
            else
                targetTable.rows.item(rowIndex).style.display = 'table-row';
        }
    }