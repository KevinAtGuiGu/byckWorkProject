/*
*/
function ajaxPost(options) {
    var op = {};
    $.extend(op, {
        cache: true,
        type: "POST",
        error: function (xhr, ajaxOptions, thrownError) {
            //console.log(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            //TODO error process window.location.href = "error-500.html";
            if (xhr.responseText.indexOf("SESSIONTIMEOUT") > 0 || xhr.responseText.indexOf("账号登录") > 0) {//登陆超时
                weui.topTips("登录超时，系统将5秒内自动返回登录页面", 3000);
            }
        },
        complete: function(xmlHttp,textStatus) {
            var redirect = xmlHttp.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向
            if (redirect == "REDIRECT") {
                window.location.href=xmlHttp.getResponseHeader("CONTEXTPATH");
            }
        }
    },options);
    $.ajax(op);
}
function getURLVar(key) {
    var value = [];

    var query = String(document.location).split('?');

    if (query[1]) {
        var part = query[1].split('&');

        for (i = 0; i < part.length; i++) {
            var data = part[i].split('=');

            if (data[0] && data[1]) {
                value[data[0]] = data[1];
            }
        }

        if (value[key]) {
            return value[key];
        } else {
            return '';
        }
    }
}
function isNullOrEmpty(obj) {
    if (typeof (obj) == "undefined") {
        return true;
    }
    if (obj === true || obj === false) {
        return false;
    }
    if (obj === 0) {
        return false;
    }
    if (obj == null || obj == "") {
        return true;
    }
    if (typeof (obj) == "object") {
        if ($.isArray(obj)) {
            if (obj.length == 0) {
                return true;
            }
        }
    }
    return false;
}
/* FORM FUNCTIONS*/
function getCheckValue(ck) {
    var $ck = $(ck);
    return $ck[0] && ($ck[0].checked || $ck[0].getAttribute("checked") || $ck.attr("checked")) || false;
}
function getDate(val) {
    if (val.length > 19) {
        val = val.substring(0, 19);
    }
    var str = "" + val;
    if (str.length < 19 && str.length > 10 && str[10] == "T") {
        str = str.replace("T", " ");
        return str;
    }
    else {
        return val;
    }
}
function loadForm(form, obj) {
    if (obj == null) {
        obj = form;
        form = null;
    }
    if (form == null) {
        form = $(document.body);
    }
    for (var key in obj) {
        var $dom = form.find("[name='" + key + "']");
        if ($dom.length == 0) {
            $dom = form.find("#" + key);
        }
        $dom.each(function () {
            setControl(this, key, obj[key]);
        });
    }
}
function setControl(control, key, value) {
    var $control = $(control);
    var tagName = $control[0].tagName;
    var type = $control.attr('type');
    if (value == null || value == "null") {
        value = "";
    }
    //value = HTMLDecode(value);
    if (tagName == 'INPUT') {
        //console.log(type);
        if (type == 'radio') {
            $control.attr('checked', $control.val() == value);
        } else if (type == 'checkbox') {
            var val = value > 0 ? true : false;
            var check = getCheckValue($control);
            if (check != val) {
                $control.click();
            }
        } else if (type == 'date') {
            var str = "" + value;
            if (str.length >= 10) {
                str = str.substr(0, 10);
            }
            $control.val(str);
        } else if (type == 'time') {
            var str = "" + value;
            if (str.length >= 10) {
                str = str.substr(11, 8);
            }
            $control.val(str);
        } else if (type == 'datetime-local') {
            $control.val(getDate(value));
        }
        else {
            $control.val(value);
        }
    } else if (tagName == 'SELECT') {
        $control.val(value);
    } else if (tagName == 'TEXTAREA') {
        $control.text(value);
        $control.val(value);
    }
    else {
        $control.text(value);
    }
}
function resetForm(form, func) {
    form[0].reset();
    for (var i = 0; i < form.find("[type='hidden']").length; i++) {
        form.find("[type='hidden']").eq(i).val('');
    }
    if (func != null && func != undefined) {
        func && func();
    }
}

/**
 * Created by jf on 2015/9/11.
 * Modified by bear on 2016/9/7.
 */
// $(function () {
//     var pageManager = {
//         $container: $('#container'),
//         _pageStack: [],
//         _configs: [],
//         _pageAppend: function(){},
//         _defaultPage: null,
//         _pageIndex: 1,
//         setDefault: function (defaultPage) {
//             this._defaultPage = this._find('name', defaultPage);
//             return this;
//         },
//         setPageAppend: function (pageAppend) {
//             this._pageAppend = pageAppend;
//             return this;
//         },
//         init: function () {
//             var self = this;

//             $(window).on('hashchange', function () {
//                 var state = history.state || {};
//                 var url = location.hash.indexOf('#') === 0 ? location.hash : '#';
//                 var page = self._find('url', url) || self._defaultPage;
//                 if (state._pageIndex <= self._pageIndex || self._findInStack(url)) {
//                     self._back(page);
//                 } else {
//                     self._go(page);
//                 }
//             });

//             if (history.state && history.state._pageIndex) {
//                 this._pageIndex = history.state._pageIndex;
//             }

//             this._pageIndex--;

//             var url = location.hash.indexOf('#') === 0 ? location.hash : '#';
//             var page = self._find('url', url) || self._defaultPage;
//             this._go(page);
//             return this;
//         },
//         push: function (config) {
//             this._configs.push(config);
//             return this;
//         },
//         go: function (to) {
//             var config = this._find('name', to);
//             if (!config) {
//                 return;
//             }
//             location.hash = config.url;
//         },
//         _go: function (config) {
//             this._pageIndex ++;

//             history.replaceState && history.replaceState({_pageIndex: this._pageIndex}, '', location.href);

//             var html = $(config.template).html();
//             var $html = $(html).addClass('slideIn').addClass(config.name);
//             $html.on('animationend webkitAnimationEnd', function(){
//                 $html.removeClass('slideIn').addClass('js_show');
//             });
//             this.$container.append($html);
//             this._pageAppend.call(this, $html);
//             this._pageStack.push({
//                 config: config,
//                 dom: $html
//             });

//             if (!config.isBind) {
//                 this._bind(config);
//             }

//             return this;
//         },
//         back: function () {
//             history.back();
//         },
//         _back: function (config) {
//             this._pageIndex --;

//             var stack = this._pageStack.pop();
//             if (!stack) {
//                 return;
//             }

//             var url = location.hash.indexOf('#') === 0 ? location.hash : '#';
//             var found = this._findInStack(url);
//             if (!found) {
//                 var html = $(config.template).html();
//                 var $html = $(html).addClass('js_show').addClass(config.name);
//                 $html.insertBefore(stack.dom);

//                 if (!config.isBind) {
//                     this._bind(config);
//                 }

//                 this._pageStack.push({
//                     config: config,
//                     dom: $html
//                 });
//             }

//             stack.dom.addClass('slideOut').on('animationend webkitAnimationEnd', function () {
//                 stack.dom.remove();
//             });

//             return this;
//         },
//         _findInStack: function (url) {
//             var found = null;
//             for(var i = 0, len = this._pageStack.length; i < len; i++){
//                 var stack = this._pageStack[i];
//                 if (stack.config.url === url) {
//                     found = stack;
//                     break;
//                 }
//             }
//             return found;
//         },
//         _find: function (key, value) {
//             var page = null;
//             for (var i = 0, len = this._configs.length; i < len; i++) {
//                 if (this._configs[i][key] === value) {
//                     page = this._configs[i];
//                     break;
//                 }
//             }
//             return page;
//         },
//         _bind: function (page) {
//             var events = page.events || {};
//             for (var t in events) {
//                 for (var type in events[t]) {
//                     this.$container.on(type, t, events[t][type]);
//                 }
//             }
//             page.isBind = true;
//         }
//     };

//     function fastClick(){
//         var supportTouch = function(){
//             try {
//                 document.createEvent("TouchEvent");
//                 return true;
//             } catch (e) {
//                 return false;
//             }
//         }();
//         var _old$On = $.fn.on;

//         $.fn.on = function(){
//             if(/click/.test(arguments[0]) && typeof arguments[1] == 'function' && supportTouch){ // 只扩展支持touch的当前元素的click事件
//                 var touchStartY, callback = arguments[1];
//                 _old$On.apply(this, ['touchstart', function(e){
//                     touchStartY = e.changedTouches[0].clientY;
//                 }]);
//                 _old$On.apply(this, ['touchend', function(e){
//                     if (Math.abs(e.changedTouches[0].clientY - touchStartY) > 10) return;

//                     e.preventDefault();
//                     callback.apply(this, [e]);
//                 }]);
//             }else{
//                 _old$On.apply(this, arguments);
//             }
//             return this;
//         };
//     }
//     function preload(){
//         $(window).on("load", function(){
//             var imgList = [
//                 "./images/layers/content.png",
//                 "./images/layers/navigation.png",
//                 "./images/layers/popout.png",
//                 "./images/layers/transparent.gif"
//             ];
//             for (var i = 0, len = imgList.length; i < len; ++i) {
//                 new Image().src = imgList[i];
//             }
//         });
//     }
//     function androidInputBugFix(){
//         // .container 设置了 overflow 属性, 导致 Android 手机下输入框获取焦点时, 输入法挡住输入框的 bug
//         // 相关 issue: https://github.com/weui/weui/issues/15
//         // 解决方法:
//         // 0. .container 去掉 overflow 属性, 但此 demo 下会引发别的问题
//         // 1. 参考 http://stackoverflow.com/questions/23757345/android-does-not-correctly-scroll-on-input-focus-if-not-body-element
//         //    Android 手机下, input 或 textarea 元素聚焦时, 主动滚一把
//         if (/Android/gi.test(navigator.userAgent)) {
//             window.addEventListener('resize', function () {
//                 if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
//                     window.setTimeout(function () {
//                         document.activeElement.scrollIntoViewIfNeeded();
//                     }, 0);
//                 }
//             })
//         }
//     }
//     function setJSAPI(){
//         var option = {
//             title: 'WeUI, 为微信 Web 服务量身设计',
//             desc: 'WeUI, 为微信 Web 服务量身设计',
//             link: "https://weui.io",
//             imgUrl: 'https://mmbiz.qpic.cn/mmemoticon/ajNVdqHZLLA16apETUPXh9Q5GLpSic7lGuiaic0jqMt4UY8P4KHSBpEWgM7uMlbxxnVR7596b3NPjUfwg7cFbfCtA/0'
//         };

//         $.getJSON('https://weui.io/api/sign?url=' + encodeURIComponent(location.href.split('#')[0]), function (res) {
//             wx.config({
//                 beta: true,
//                 debug: false,
//                 appId: res.appid,
//                 timestamp: res.timestamp,
//                 nonceStr: res.nonceStr,
//                 signature: res.signature,
//                 jsApiList: [
//                     'onMenuShareTimeline',
//                     'onMenuShareAppMessage',
//                     'onMenuShareQQ',
//                     'onMenuShareWeibo',
//                     'onMenuShareQZone',
//                     // 'setNavigationBarColor',
//                     'setBounceBackground'
//                 ]
//             });
//             wx.ready(function () {
//                 /*
//                  wx.invoke('setNavigationBarColor', {
//                  color: '#F8F8F8'
//                  });
//                  */
//                 wx.invoke('setBounceBackground', {
//                     'backgroundColor': '#F8F8F8',
//                     'footerBounceColor' : '#F8F8F8'
//                 });
//                 wx.onMenuShareTimeline(option);
//                 wx.onMenuShareQQ(option);
//                 wx.onMenuShareAppMessage({
//                     title: 'WeUI',
//                     desc: '为微信 Web 服务量身设计',
//                     link: location.href,
//                     imgUrl: 'https://mmbiz.qpic.cn/mmemoticon/ajNVdqHZLLA16apETUPXh9Q5GLpSic7lGuiaic0jqMt4UY8P4KHSBpEWgM7uMlbxxnVR7596b3NPjUfwg7cFbfCtA/0'
//                 });
//             });
//         });
//     }
//     function setPageManager(){
//         var pages = {}, tpls = $('script[type="text/html"]');

//         for (var i = 0, len = tpls.length; i < len; ++i) {
//             var tpl = tpls[i], name = tpl.id.replace(/tpl_/, '');
//             pages[name] = {
//                 name: name,
//                 url: '#' + name,
//                 template: '#' + tpl.id
//             };
//         }
//         pages.home.url = '#';

//         for (var page in pages) {
//             pageManager.push(pages[page]);
//         }
//         pageManager
//             .setPageAppend(function($html){
//                 var $foot = $html.find('.page__ft');
//                 if($foot.length < 1) return;

//                 var winH = $(window).height();
//                 if($foot.position().top + $foot.height() < winH){
//                     $foot.addClass('j_bottom');
//                 }else{
//                     $foot.removeClass('j_bottom');
//                 }
//             })
//             .setDefault('home')
//             .init();
//     }

//     function init(){
//         preload();
//         fastClick();
//         androidInputBugFix();
//         //setJSAPI();
//         setPageManager();

//         window.pageManager = pageManager;
//         window.home = function(){
//             location.hash = '';
//         };
//     }
//     init();
// });
