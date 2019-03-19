/**
 *
 */
function changeVerifyCode(img) {
    img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}

/**
 * 获取URL传入对应参数的值 eg:/o2o/shopadmin/getshopbyid?shopId=1; param:'shopId'; result:return 1
 * @param name
 * @returns {string}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (null != r) {
        return decodeURIComponent(r[2]);
    }
    return "";
}