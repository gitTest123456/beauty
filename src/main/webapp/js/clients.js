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
            url: "/beauty/clients",
            // Default attributes for the Contact item
            defaults: function () {
                return {
                    clientId: null,
                    firstName: null,
                    surName: null,
                    lastName: null,
                    telephone: null
                };
            },
            validation: {
                firstName: {
                    minLength: 5,
                    msg: 'Please enter a name'
                },
                surName: {
                    minLength: 5,
                    msg: 'Please enter a surName'
                },
                lastName: {
                    minLength: 5,
                    msg: 'Please enter a valid lastName'
                },
                telephone: {
                    minLength: 5,
                    msg: 'Please enter a valid telephone'
                }
            }
        }
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


    /**********************************************************************************
     **                     Views
     **********************************************************************************/
    var ClientListView = Backbone.View.extend(
        {

            template: _.template($('#client-list-template').html()),
            el: $("#client-list-block"),

            render: function () {
                $(this.el).html(this.template({client: this.collection}));
                $("#client-list-block").tablesorter();
                return this;
            }

        }
    );


    var ClientView = Backbone.View.extend(
        {

            template: _.template($('#client-details-template').html()),
            el: $("#client-details-block"), // DOM element with contact details,

            events: {
                "click  #form-btn-remove": "deleteClient"
            },
            render: function () {
                $(this.el).html(this.template({
                    client: this.model
                }))
            },
            deleteClient: function (e) {
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
            }}
    );


    var Controller = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/client_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/add": "add",
            "!/edit/:itemIndex": "edit"
        },

        list: function () {
            clientCollection.fetch();
        },
        add: function () {
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
                        window.location = "/";
                    },
                    error: function (model, error) {
                        alert("Some exception when save data");
                    }
                });

        },

        edit: function (itemIndex) {
            clientView.model = clientCollection[itemIndex];
            clientView.render();
        }
    });

    var clientCollection = new ClientCollection();
    var clientListView = new ClientListView({collection: clientCollection});
    clientListView.render();
    var curClient = new Client();
    var clientView = new ClientView({model: curClient});
    clientView.render();
    var controller = new Controller();
    Backbone.history.start();
})

