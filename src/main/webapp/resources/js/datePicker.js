$(document).ready(function() {
    $("#startDate, #endDate, #birthday, #reviewDate").datepicker({
        showOn: 'button',
        buttonText: 'Show Date',
        buttonImageOnly: true,
        buttonImage: 'http://jqueryui.com/resources/demos/datepicker/images/calendar.gif',
        dateFormat: 'dd/mm/yy'
    });
    $(".ui-datepicker-trigger").mouseover(function() {
        $(this).css('cursor', 'pointer');
    });

});