/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 08.08.13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */


//Load application with JQuery when DOM is ready
$(function () {

    /**********************************************************************************
     **                     Models
     **********************************************************************************/
    var Invest = Backbone.Model.extend(
        {
            url: "/beauty/invest/add",
            // Default attributes for the Invest item
            defaults: function () {
                return {
                    investId: null,
                    arenda: null,
                    commonReqEnv: null,
                    dateReq: null
                };
            }
        });
    /**********************************************************************************
     **                     Views
     **********************************************************************************/
    var InvestListView = Backbone.View.extend(
        {

            template: _.template($('#invest-list-template').html()),
            el: $("#invest-list-block"),
            initialize: function (options) {
                _.bindAll(this, 'beforeRender', 'render', 'afterRender');
                var _this = this;
                this.render = _.wrap(this.render, function (render) {
                    _this.beforeRender();
                    render();
                    _this.afterRender();
                });
            },

            beforeRender: function () {
                console.log('beforeRender');
            },

            render: function () {
                $(this.el).html(this.template({invest: this.collection}));
                return this;
            },

            afterRender: function () {
            }  }


    ); //Backbone.Model.extend end


    // The collection of invests
    var InvestCollection = Backbone.Collection.extend(
        {
            model: Invest,
            url: "/beauty/invest",
            parse: function (response) {
                console.log('Parsing list of the invest:' + response);
                investCollection = response;
                investListView.collection = investCollection;
                investListView.render();
                return response;
            }
        }
    );


    var InvestView = Backbone.View.extend(
        {

            template: _.template($('#invest-details-template').html()),
            el: $("#invest-details-block"), // DOM element with invest details,

            events: {
                "click  #form-btn-remove": "deleteInvest",
                "click  #form-btn-add": "addInvest",
                "click  #form-btn-report": "getCVSInvestReport",
                "click  #form-btn-report1": "getCVSReport"
            },
            render: function () {
                $(this.el).html(this.template({
                    invest: this.model
                }))

                if (this.model != null && this.model.data != null) {
                    $("#datetimepicker3").data('datetimepicker').setDate(this.model.dateReq);
                }
            },
            deleteInvest: function (e) {
                e.preventDefault();
                if (jQuery("#invest-details-block").valid()) {
                    $.ajax({
                        type: "post",
                        url: "/beauty/invest/delete", //your valid url
                        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                        data: JSON.stringify(this.model), //json object or array of json objects
                        success: function (result) {
                            window.location = "/invest.html";
                        },
                        error: function () {
                            window.location = "/invest.html";
                        }
                    });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            },
            getCVSInvestReport: function (e) {
                e.preventDefault();
                $.ajax({
                    type: "post",
                    url: "/beauty/invest/investReport", //your valid url
                    contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                    success: function (result) {
                        window.location = "/invest.html";
                    },
                    error: function () {
                        window.location = "/invest.html";
                    }
                });
            },
            getCVSReport: function (e) {
                e.preventDefault();
                $.ajax({
                    type: "post",
                    url: "/beauty/invest/report", //your valid url
                    success: function (result) {
                        window.location = "/invest.html";
                    },
                    error: function () {
                        window.location = "/invest.html";
                    }
                });
            },
            addInvest: function (e) {
                if (jQuery("#invest-details-block").valid()) {
                    var arenda = $("#inputArenda").val();
                    var commonReqEnv = $('#inputСommonReqEnv').val();
                    var dateReq = $('#inputDateReq').val();
                    var id = $('#inputId').val();
                    var newInvest = new Invest(
                        {   "investId": id,
                            "arenda": arenda,
                            "commonReqEnv": commonReqEnv,
                            "dateReq": dateReq
                        }).save({}, {
                            wait: true,
                            success: function (model, response) {
                                window.location = "/invest.html";
                            },
                            error: function (model, error) {
                                window.location = "/invest.html";
                            }
                        });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            }
        }
    );


    var InvestController = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/invest_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/edit/:itemIndex": "edit",
            "!/back": "back"
        },

        list: function () {
            investCollection.fetch();
        },

        edit: function (itemIndex) {
            investView.model = investCollection[itemIndex];
            investView.render();
            if (investView.model != null && investView.model.dateReq != null) {
                $("#datetimepicker3").data('datetimepicker').setDate(investView.model.dateReq);
            }
         },
        back: function () {
            window.location = "/mainMenu.html"
        }
    });

    var investCollection = new InvestCollection();
    var investListView = new InvestListView({collection: investCollection});
    investListView.render();
    var curInvest = new Invest();
    var investView = new InvestView({model: curInvest});
    investView.render();
    var controller = new InvestController();
    var timer_pagination = null;
    Backbone.history.start();
})

