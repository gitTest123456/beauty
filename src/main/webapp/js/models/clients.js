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
    var Client = Backbone.Model.extend(
        {
            url: "/beauty/clients/add",
            // Default attributes for the Contact item
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
    /**********************************************************************************
     **                     Views
     **********************************************************************************/
    var ClientListView = Backbone.View.extend(
        {

            template: _.template($('#client-list-template').html()),
            el: $("#client-list-block"),
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
                $(this.el).html(this.template({client: this.collection}));
                return this;
            },

            afterRender: function () {
            }  }


    ); //Backbone.Model.extend end


    // The collection of contacts
    var ClientCollection = Backbone.Collection.extend(
        {
            model: Client,
            url: "/beauty/clients",
            parse: function (response) {
                console.log('Parsing list of the clients:' + response);
                clientCollection = response;
                clientListView.collection = clientCollection;
                clientListView.render();
                return response;
            }
        }
    );


    var ClientView = Backbone.View.extend(
        {

            template: _.template($('#client-details-template').html()),
            el: $("#client-details-block"), // DOM element with contact details,

            events: {
                "click  #form-btn-remove": "deleteClient",
                "click #form-btn-add": "addClient"
            },
            render: function () {
                $(this.el).html(this.template({
                    client: this.model
                }))
            },
            deleteClient: function (e) {
                e.preventDefault();
                if (jQuery("#client-details-block").valid()) {
                    $.ajax({
                        type: "post",
                        url: "/beauty/clients/delete", //your valid url
                        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
                        data: JSON.stringify(this.model), //json object or array of json objects
                        success: function (result) {
                            //do nothing
                        },
                        error: function () {
                            //todo
                        }
                    });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            },
            addClient: function (e) {
                if (jQuery("#client-details-block").valid()) {
                    var firstName = $("#inputName").val();
                    var surName = $('#inputSurName').val();
                    var lastName = $('#inputLastName').val();
                    var telephone = $('#inputPhone').val();
                    var id = $('#inputId').val();

                    var newClient = new Client(
                        {   "clientId": id,
                            "firstName": firstName,
                            "surName": surName,
                            "lastName": lastName,
                            "telephone": telephone}).save({}, {
                            wait: true,
                            success: function (model, response) {
                                window.location = "/clients.html";
                            },
                            error: function (model, error) {
                                window.location = "/clients.html";
                            }
                        });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            }
        }
    );


    var Controller = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/client_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/edit/:itemIndex": "edit",
            "!/back": "back"
        },

        list: function () {
            clientCollection.fetch();
        },

        edit: function (itemIndex) {
            clientView.model = clientCollection[itemIndex];
            clientView.render();
        },
        back: function () {
            window.location = "/"
        }
    });

    var clientCollection = new ClientCollection();
    var clientListView = new ClientListView({collection: clientCollection});
    clientListView.render();
    var curClient = new Client();
    var clientView = new ClientView({model: curClient});
    clientView.render();
    var controller = new Controller();
    var timer_pagination = null;
    Backbone.history.start();
})

