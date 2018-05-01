$(function () {

    choose_bg();
    //changeCode();

    $("#captcha_img").click(function () {
        changeCode();
    });
    $("#register_form").submit(function () {
        var issubmit = true;
        var i_index = 0;
        $(this).find('.form-control').each(function (i) {
            if ($.trim($(this).val()).length == 0) {
                $(this).css('border', '1px #ff0000 solid');
                issubmit = false;
                if (i_index == 0)
                    i_index = i;
            }
        });

        if (!isPoneAvailable($.trim($("#j_phone").val()))){
            $("#j_phone").val("");
            $("#j_phone").css('border', '1px #ff0000 solid');
            $("#j_phone").focus();

            return false;
        }

        if (!issubmit) {
            $(this).find('.form-control').eq(i_index).focus();
            return false;
        }

        $("#login_ok").attr("disabled", true).val('正在注册中..');

    });
});

function isPoneAvailable(str) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}

function changeCode() {
    $("#captcha_img").attr("src", "getGifCode?t=" + (new Date().getTime()));
}

function choose_bg() {
    var bg = Math.floor(Math.random() * 9 + 1);
    $('body').css('background-image', 'url(images/login/loginbg_0' + bg + '.jpg)');
}