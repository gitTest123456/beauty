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
        events: {
            "change #selectId1": "separationSelected"
        },
        separationSelected: function () {
            serviceListView = new ServiceOptionListView().render();
        },
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
                serviceListView.collection = servicesCollectionCopy;
                serviceListView = new ServiceOptionListView().render();
                return response;
            }
        }
    );

    var ServiceOptionListView = Backbone.View.extend({
        template: _.template($('#services-details-template').html()),
        el: $("#services-details-block"), // DOM element with contact details,
        render: function () {
            servicesCollectionCopy = [];
            var employerIndex = $("#selectId1").val();
            if (employerIndex != null && employersCollection.length > 0) {
                var employer = employersCollection[employerIndex];
                var curSeparation = employer.separation.separationId;
                var j = 0;
                for (var i = 0; i < servicesCollection.length; i++) {
                    if (servicesCollection[i].separation_.separationId == curSeparation) {
                        servicesCollectionCopy[j++] = servicesCollection[i];
                    }
                }

                $(this.el).html(this.template({
                    service: servicesCollectionCopy
                }))
            }
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
                $(this.el).html(this.template({statistic: statisticCollection}));
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


    var StatisticView = Backbone.View.extend(
        {

            template: _.template($('#statistic-details-template').html()),
            el: $("#statistic-details-block"), // DOM element with contact details,

            events: {
                "click  #form-btn-remove": "deleteStatistic",
                "click #form-btn-add": "addStatistic",
                "click #form-btn-report": "getCVSReport"
            },
            render: function () {
                $(this.el).html(this.template({
                    statistic: this.model
                }))

                if (this.model != null && this.model.data != null) {
                    $("#datetimepicker1").data('datetimepicker').setDate(this.model.dateVisit);
                    $("#datetimepicker2").data('datetimepicker').setDate(this.model.timeVisit);
                }
            },
            deleteStatistic: function (e) {
                e.preventDefault();
                $.ajax({
                    type: "post",
                    url: "/beauty/statistics/delete", //your valid url
                    contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                    data: JSON.stringify(this.model), //json object or array of json objects
                    success: function (result) {
                        window.location = "/statistic.html";
                    },
                    error: function () {
                        window.location = "/statistic.html";
                    }
                });
            },
            getCVSReport: function (e) {
                var employerIndex = $("#selectId").val();
                var employer = employersCollection[employerIndex];
                e.preventDefault();
                $.ajax({
                    type: "post",
                    url: "/beauty/statistics/report", //your valid url
                    contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                    data: JSON.stringify(employer), //json object or array of json objects
                    success: function (result) {
                        window.location = "/statistic.html";
                    },
                    error: function () {
                        window.location = "/statistic.html";
                    }
                });
            },
            employerByEmployerId: null,
            clientByClientId: null,
            serviceByServiceId: null,
            addStatistic: function (e) {
                var visitId = $("#inputStatisticId").val();
                var dateVisit = $('#inputStatisticData').val();
                var timeVisit = $('#inputStatisticTime').val();
                var employerIndex = $("#selectId").val();
                var employer = employersCollection[employerIndex];
                var clientIndex = $("#selectId1").val();
                var client = clientsCollection[clientIndex];
                var serviceIndex = $("#selectId2").val();
                var service = servicesCollectionCopy[serviceIndex];

                var newStatistic = new Statistic(
                    {
                        "visitId": visitId,
                        "dateVisit": dateVisit,
                        "timeVisit": timeVisit,
                        "employerByEmployerId": employer,
                        "clientByClientId": client,
                        "serviceByServiceId": service
                    }).save({}, {
                        wait: true,
                        success: function (model, response) {
                            window.location = "/statistic.html";
                        },
                        error: function (model, error) {
                            window.location = "/statistic.html";
                        }
                    });
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
            "!/back": "back",
            "!/serviceUpdate": "serviceUpdate"
        },

        list: function () {
            clientsCollection.fetch();
            employersCollection.fetch();
            servicesCollectionCopy.fetch();
            statisticCollection.fetch();
        },

        serviceUpdate: function () {
            serviceListView = new ServiceOptionListView().render();
        },

        edit: function (itemIndex) {
            statisticView.model = statisticCollection[itemIndex];
            statisticView.render();
            for (index = 0; index < clientsCollection.length; index++) {
                if (statisticCollection[itemIndex].clientByClientId.clientId == clientsCollection[index].clientId) {
                    $("#selectId").val(index);
                    break;
                }
            }

            for (index = 0; index < clientsCollection.length; index++) {
                if (statisticCollection[itemIndex].employerByEmployerId.employerId == employersCollection[index].employerId) {
                    $("#selectId1").val(index);
                    break;
                }
            }

            for (index = 0; index < servicesCollectionCopy.length; index++) {
                if (statisticCollection[itemIndex].serviceByServiceId.serviceId == servicesCollectionCopy[index].serviceId) {
                    $("#selectId2").val(index);
                    break;
                }
            }

            if (statisticView.model != null && statisticView.model.dateVisit != null) {
                $("#datetimepicker1").data('datetimepicker').setDate(statisticView.model.dateVisit);
                $("#datetimepicker2").data('datetimepicker').setDate(statisticView.model.timeVisit);
            }


        },
        back: function () {
            window.location = "/mainMenu.html"
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
    var statisticListView = new StatisticListView({collection: statisticCollection});
    statisticListView.render();


    var curStatistic = new Statistic();
    var statisticView = new StatisticView({model: curStatistic});
    statisticView.render();
    var controller = new StatisticController();
    var servicesCollectionCopy = new ServiceCollection();

    Backbone.history.start();
});