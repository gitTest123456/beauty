/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */

//Load application with JQuery when DOM is ready
$(function () {

    /**********************************************************************************
     **                     Models
     **********************************************************************************/
    var ServiceModel = Backbone.Model.extend(
        {
            url: "/beauty/services/add",
            // Default attributes for the service item
            defaults: function () {
                return {
                    serviceId: null,
                    naming: null,
                    data: null,
                    cost: null,
                    separation_: null
                };
            }
        });
    /**********************************************************************************
     **                     Views
     **********************************************************************************/
    var ServiceListView = Backbone.View.extend(
        {

            template: _.template($('#service-list-template').html()),
            el: $("#service-list-block"),
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
                $(this.el).html(this.template({service: this.collection}));
                return this;
            },

            afterRender: function () {
            }  }


    ); //Backbone.Model.extend end

    var Separation = Backbone.Model.extend(
        {
            defaults: function () {
                return {
                    separationId: null,
                    separationName: null
                };
            }
        });

    // The collection of services
    var SeparationCollection = Backbone.Collection.extend(
        {
            model: Separation,
            url: "/beauty/separations",
            parse: function (response) {
                console.log('Parsing list of the separations:' + JSON.stringify(response));
                separationsCollection = response;
                separationListView = new SeparationView().render();
                return response;
            }
        }
    );

    var SeparationView = Backbone.View.extend(
        {
            template: _.template($('#separation-details-template').html()),
            el: $("#separation-details-block"), // DOM element with contact details,

            render: function () {
                $(this.el).html(this.template({
                    separation: separationsCollection
                }))
            }
        }
    );

    // The collection of services
    var ServiceCollection = Backbone.Collection.extend(
        {
            model: ServiceModel,
            url: "/beauty/services",
            parse: function (response) {
                console.log('Parsing list of the services:' + JSON.stringify(response));
                servicesCollection = response;
                servicesListView.collection = servicesCollection;
                servicesListView.render();
                return response;
            }
        }
    );


    var ServiceView = Backbone.View.extend(
        {

            template: _.template($('#service-details-template').html()),
            el: $("#service-details-block"), // DOM element with contact details,

            events: {
                "click  #form-btn-remove": "deleteService",
                "click #form-btn-add": "addService"
            },
            render: function () {
                $(this.el).html(this.template({
                    service: this.model
                }))

                if (this.model != null && this.model.data != null)
                   $("#datetimepicker").data('datetimepicker').setDate(Date.parse("2012-12-12"));
            },
            deleteService: function (e) {
                e.preventDefault();
                if (jQuery("#service-details-block").valid()) {
                    $.ajax({
                        type: "post",
                        url: "/beauty/services/delete", //your valid url
                        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                        data: JSON.stringify(this.model), //json object or array of json objects
                        success: function (result) {
                            window.location = "/services.html";
                        },
                        error: function () {
                            window.location = "/services.html";
                        }
                    });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            },
            addService: function (e) {
                if (jQuery("#service-details-block").valid()) {
                    var naming = $("#inputServiceNaming").val();
                    var data = $('#inputServiceData').val();
                    var cost = $('#inputServiceCost').val();
                    var separationIndex = $("#selectId").val();
                    var separation = separationsCollection[separationIndex];
                    var id = $('#inputServiceId').val();

                    var newService = new ServiceModel(
                        {   "serviceId": id,
                            "naming": naming,
                            "data": data,
                            "cost": cost,
                            "separation_": separation}).save({}, {
                            wait: true,
                            success: function (model, response) {
                                window.location = "/services.html";
                            },
                            error: function (model, error) {
                                window.location = "/services.html";
                            }
                        });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            }
        }
    );


    var ServiceController = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/service_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/edit/:itemIndex": "edit",
            "!/back": "back"
        },

        list: function () {
            servicesCollection.fetch();
            separationsCollection.fetch();
        },

        edit: function (itemIndex) {
            serviceView.model = servicesCollection[itemIndex];
            serviceView.render();
            for (index = 0; index < separationsCollection.length; index++) {
                if (servicesCollection[itemIndex].separation_.separationName == separationsCollection[index].separationName) {
                    $("#selectId").val(index);
                    break;
                }
            }
        },
        back: function () {
            window.location = "/"
        }
    });

    var servicesCollection = new ServiceCollection();
    var servicesListView = new ServiceListView({collection: servicesCollection});
    servicesListView.render();
    var curService = new ServiceModel();
    var serviceView = new ServiceView({model: curService});
    serviceView.render();
    var separationsCollection = new SeparationCollection();
    var controller = new ServiceController();
    var separationListView = new SeparationView({collection: separationsCollection});
    separationListView.render();
    Backbone.history.start();
});