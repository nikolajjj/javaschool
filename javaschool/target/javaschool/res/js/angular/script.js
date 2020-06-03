$(document).ready(function () {
    popoverOptions = {
        content: function () {
            // Get the content from the hidden sibling.
            return $(this).siblings('.my-popover-content').html();
        },
        trigger: 'hover',
        animation: false,
        placement: 'top',
        html: true
    };
    $('.popoverHover').popover(popoverOptions);
});