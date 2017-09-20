
$(function() {
    $('#myTabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    var clipboard = new Clipboard('#copyBtn');

    clipboard.on('success', function(e) {
        console.info('Action:', e.action);
        console.info('Text:', e.text);
        console.info('Trigger:', e.trigger);

        e.clearSelection();
        layer.tips('成功复制到剪切板', '#copyBtn');
    });

    clipboard.on('error', function(e) {
        console.error('Action:', e.action);
        console.error('Trigger:', e.trigger);
        layer.tips('复制失败', '#copyBtn');
    });
});

var app = new Vue({
    el: "#input_form",
    data: {
        serviceUrlInput: "",
        serviceType: "1",
        appName: "pmis",
        action: "url",
        copyResult: "",
        redirect_url: "/UIProcessor?Table=PM_GZRZAT&operate=PM_GZRZAT_M2"
    },
    methods: {

    },
    computed: {
        serviceUrl: function() {
            var serviceUrl_ = "";
            switch(this.serviceType) {
                case "1":
                    serviceUrl_ = "http://weberp.winning.com.cn:9080/winning/wework/redirect.weworkService";
                    break;
                case "2":
                    serviceUrl_ = "http://weberp.winning.com.cn:9083/winning/wework/redirect.weworkService";
                    break;
                default:
                    serviceUrl_ = this.serviceUrlInput;
            }
            return serviceUrl_;
        },
        newUrl: function() {
            this.copyResult = "";
            return this.serviceUrl
                + "?appName=" + this.appName
                + "&action=" + this.action
                + "&url=" + encodeURIComponent(this.redirect_url);
        }
    },
    watch: {

    }
});