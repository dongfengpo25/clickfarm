/**
 * 系统工具类
 */
$(function () {
    $.fn.extend({
        /**
         * 提示
         * @param msg
         * @param options
         */
        alertInfo: function (msg, options) {
            $.fn.alertMsg("info", msg, options);
        },
        /**
         * 警告
         * @param msg
         * @param options
         */
        alertWarn: function (msg, options) {
            $.fn.alertMsg("warn", msg, options);
        },
        /**
         * 错误
         * @param msg
         * @param options
         */
        alertError: function (msg, options) {
            $.fn.alertMsg("error", msg, options);
        },
        /**
         * 正确
         * @param msg
         * @param options
         */
        alertCorrect: function (msg, options) {
            $.fn.alertMsg("correct", msg, options);
        },
        /**
         * 确认
         * @param msg
         * @param options
         */
        alertConfirm: function (msg, options) {
            var op = {
                okName: BJUI.regional.alertmsg.ok,
                okCall: null,
                cancelName: BJUI.regional.alertmsg.btnMsg.cancel,
                cancelCall: null
            };
            $.extend(op, options);
            var buttons = [
                {name: op.okName, call: op.okCall, keyCode: BJUI.keyCode.ENTER},
                {name: op.cancelName, call: op.cancelCall, keyCode: BJUI.keyCode.ESC}
            ];
            $.fn.alertMsg("confirm", msg, options);
        },


        /**
         * 弹出消息
         * @param type
         * @param msg
         * @param options
         */
        alertMsg: function (type, msg, options) {
            $("body").alertmsg(type, msg, options);
        }
    })
})