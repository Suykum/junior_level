$(document).ready(function () {
    getReservedSeats();
});
function inputValidate() {
    var place = $('input:radio[name="place"]:checked').val();
    if (place === undefined) {
        alert("Choose a seat");
        return false;
    }
    return true;
}

function getReservedSeats() {
    $.ajax({
        type: "GET",
        url: "hall.do",
        success:function (data) {
            console.log(data);
            $.each(data, function (indexInArray, value) {
                document.getElementById(value).setAttribute('style', "background-color: red");
                document.getElementById('place' + value).disabled=true;
            });
        }
    });
}
