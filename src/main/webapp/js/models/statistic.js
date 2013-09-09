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
    var Statistic = Backbone.Model.extend(
        {
            url: "/beauty/statistics/add",
            // Default attributes for the Employer item
            defaults: function () {
                return {
                    visitId: null,
                    dateVisit: null,
                    timeVisit: null,
                    employerByEmployerId: null,
                    clientByClientId: null,
                    serviceByServiceId: null
                };
            }
        });


    /****************************************************************************
     * Employer backbone model
     * @type {*}
     */
    var Employer = Backbone.Model.extend(
        {
            // Default attributes for the Employer item
            defaults: function () {
                return {
                    employerId: null,
                    firstName: null,
                    surName: null,
                    lastName: null,
                    address: null,
                    birthday: null,
                    telephone: null,
                    separation: null
                };
            }
        });

    // The collection of employers
    var EmployerCollection = Backbone.Collection.extend(
        {
            model: Employer,
            url: "/beauty/employers",
            parse: function (response) {
                console.log('Parsing list of the employers:' + JSON.stringify(response));
                employersCollection = response;
                employersListView = new EmployersOptionListView().render();
                return response;
            }
        }
    );

    var EmployersOptionListView = Backbone.View.extend({
        template: _.template($('#employers-details-template').html()),
        el: $("#employers-details-block"), // DOM element with contact details,

        render: function () {
            $(this.el).html(this.template({
                employer: employersCollection
            }))
        }
    });

    /****************************************************************************
     * Clients backbone model
     * @type {*}
     */
    var Client = Backbone.Model.extend(
        {
            // Default attributes for the Client item
            defaults: function () {
                return {
                    clientId: null,
                    firstName: null,
                    surName: null,
                    lastName: null,
                    telephone: null
                };
            }
        });

    // The collection of employers
    var ClientsCollection = Backbone.Collection.extend(
        {
            model: Client,
            url: "/beauty/clients",
            parse: function (response) {
                console.log('Parsing list of the clients:' + JSON.stringify(response));
                clientsCollection = response;
                clientsListView = new ClientsOptionListView().render();
                return response;
            }
        }
    );

    var ClientsOptionListView = Backbone.View.extend({
        template: _.template($('#clients-details-template').html()),
        el: $("#clients-details-block"), // DOM element with contact details,

        render: function () {
            $(this.el).html(this.template({
                client: clientsCollection
            }))
        }
    });

    /***********************************************
     *    Service collection
     * @type {*}
     */
    var Service = Backbone.Model.extend(
        {
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

    // The collection of services
    var ServiceCollection = Backbone.Collection.extend(
        {
            model: Service,
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


    var ServicesOptionListView = Backbone.View.extend({
        template: _.template($('#services-details-template').html()),
        el: $("#services-details-block"), // DOM element with contact details,

        render: function () {
            $(this.el).html(this.template({
                service: servicesCollection
            }))
        }
    });


//    /**********************************************************************************
//     **                     Views
//     **********************************************************************************/
//    var EmployerListView = Backbone.View.extend(
//        {
//
//            template: _.template($('#employer-list-template').html()),
//            el: $("#employer-list-block"),
//            initialize: function (options) {
//                _.bindAll(this, 'beforeRender', 'render', 'afterRender');
//                var _this = this;
//                this.render = _.wrap(this.render, function (render) {
//                    _this.beforeRender();
//                    render();
//                    _this.afterRender();
//                });
//            },
//
//            beforeRender: function () {
//                console.log('beforeRender');
//            },
//
//            render: function () {
//                $(this.el).html(this.template({employer: this.collection}));
//                return this;
//            },
//
//            afterRender: function () {
//            }  }
//
//
//    ); //Backbone.Model.extend end
//
//
//    // The collection of employers
//    var EmployerCollection = Backbone.Collection.extend(
//        {
//            model: Employer,
//            url: "/beauty/employers",
//            parse: function (response) {
//                console.log('Parsing list of the employers:' + JSON.stringify(response));
//                employersCollection = response;
//                employersListView.collection = employersCollection;
//                employersListView.render();
//                return response;
//            }
//        }
//    );
//
//
//    var EmployerView = Backbone.View.extend(
//        {
//
//            template: _.template($('#employer-details-template').html()),
//            el: $("#employer-details-block"), // DOM element with contact details,
//
//            events: {
//                "click  #form-btn-remove": "deleteEmployer",
//                "click #form-btn-add": "addEmployer"
//            },
//            render: function () {
//                $(this.el).html(this.template({
//                    employer: this.model
//                }))
//            },
//            deleteEmployer: function (e) {
//                e.preventDefault();
//                if (jQuery("#employer-details-block").valid()) {
//                    $.ajax({
//                        type: "post",
//                        url: "/beauty/employers/delete", //your valid url
//                        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
//                        data: JSON.stringify(this.model), //json object or array of json objects
//                        success: function (result) {
//                            window.location = "/employers.html";
//                        },
//                        error: function () {
//                            window.location = "/employers.html";
//                        }
//                    });
//                } else {
//                    $.fancybox.open($("#dialog"));
//                }
//            },
//            addEmployer: function (e) {
//                if (jQuery("#employer-details-block").valid()) {
//                    var firstName = $("#inputEmployerName").val();
//                    var surName = $('#inputEmployerSurName').val();
//                    var lastName = $('#inputEmployerLastName').val();
//                    var telephone = $('#inputEmployerPhone').val();
//                    var address = $('#inputEmployerAddress').val();
//                    var birthday = $('#inputEmployerBirthday').val();
//                    var separationIndex = $("#selectId").val();
//                    var separation = separationsCollection[separationIndex];
//                    var id = $('#inputEmployerId').val();
//
//                    var newEmployer = new Employer(
//                        {   "employerId": id,
//                            "firstName": firstName,
//                            "surName": surName,
//                            "lastName": lastName,
//                            "telephone": telephone,
//                            "address": address,
//                            "birthday": birthday,
//                            "separation": separation}).save({}, {
//                            wait: true,
//                            success: function (model, response) {
//                                window.location = "/employers.html";
//                            },
//                            error: function (model, error) {
//                                window.location = "/employers.html";
//                            }
//                        });
//                } else {
//                    $.fancybox.open($("#dialog"));
//                }
//            }
//        }
//    );


    var StatisticController = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/statistic_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/edit/:itemIndex": "edit",
            "!/back": "back"
        },

        list: function () {
            clientsCollection.fetch();
            employersCollection.fetch();
            servicesCollection.fetch();
        },

        edit: function (itemIndex) {


        },
        back: function () {
            window.location = "/"
        }
    });
    /**********
     * for employers view level
     * @type {EmployerCollection}
     */
    var employersCollection = new EmployerCollection();
    var employersListView = new EmployersOptionListView({collection: employersCollection});
    employersListView.render();

    /******************************
     * Clients
     */
    var clientsCollection = new ClientsCollection();
    var clientsListView = new ClientsOptionListView();
    clientsListView.render();

    /**
     * Services
     * @type {Services}
     */
    var servicesCollection = new ServiceCollection();
    var servicesListView = ServicesOptionListView();
    servicesListView.render();
//
//    var curEmployer = new Employer();
//    var employerView = new EmployerView({model: curEmployer});
//    employerView.render();
//    var separationsCollection = new SeparationCollection();
      var controller = new StatisticController();
//    var separationListView = new SeparationView({collection: separationsCollection});
//    separationListView.render();
    Backbone.history.start();
});