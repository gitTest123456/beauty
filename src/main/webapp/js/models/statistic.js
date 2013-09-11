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
                serviceListView.collection = servicesCollection;
                serviceListView = new ServiceOptionListView().render();
                return response;
            }
        }
    );

    var ServiceOptionListView = Backbone.View.extend({
        template: _.template($('#services-details-template').html()),
        el: $("#services-details-block"), // DOM element with contact details,

        render: function () {
            $(this.el).html(this.template({
                service: servicesCollection
            }))
        }
    });

    var StatisticListView = Backbone.View.extend(
        {

            template: _.template($('#statistic-list-template').html()),
            el: $("#statistic-list-block"),
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
                $(this.el).html(this.template({statistic: this.collection}));
                return this;
            },

            afterRender: function () {
            }  }


    ); //Backbone.Model.extend end


    var StatisticCollection = Backbone.Collection.extend(
        {
            model: Statistic,
            url: "/beauty/statistics",
            parse: function (response) {
                console.log('Parsing list of the statistic:' + JSON.stringify(response));
                statisticCollection = response;
                statisticListView.collection = statisticCollection;
                statisticListView = new StatisticListView().render();
                return response;
            }
        }
    );


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

    var employersCollection = new EmployerCollection();
    var employersListView = new EmployersOptionListView({collection: employersCollection});
    employersListView.render();


    var clientsCollection = new ClientsCollection();
    var clientsListView = new ClientsOptionListView();
    clientsListView.render();


    var servicesCollection = new ServiceCollection();
    var serviceListView = new ServiceOptionListView();
    serviceListView.render();

    var statisticCollection = new StatisticCollection();
    var statisticListView = new StatisticListView({collection : statisticCollection});
    statisticListView.render();

    var controller = new StatisticController();


    Backbone.history.start();
});