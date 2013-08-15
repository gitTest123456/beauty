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
            el: $("#client-list-block"), // DOM element with contact list

            template: _.template($('#client-list-template').html()),

            render: function () {
                $(this.el).html(this.template({client: this.collection}));
                $("#client-list-block").tablesorter();
                return this;
            }
        }
    );


    var Controller = Backbone.Router.extend({
        routes: {
            "": "list",
            "!/clients": "list",
            "!/clients/delete/:itemIndex": "delete",
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
                        window.location = "";
                    },
                    error: function (model, error) {
                        window.location = "";
                    }
                });

        },

        edit: function (itemIndex) {
            $('#inputId').val(clientCollection[itemIndex].clientId);
            $("#inputName").val(clientCollection[itemIndex].firstName);
            $('#inputSurName').val(clientCollection[itemIndex].surName);
            $('#inputLastName').val(clientCollection[itemIndex].lastName);
            $('#inputPhone').val(clientCollection[itemIndex].telephone);
        },
        delete: function (itemIndex) {
            var model_for_delete = clientCollection[itemIndex];
            clientCollection.remove(model_for_delete);
        }
    });

    var clientCollection = new ClientCollection();
    var clientListView = new ClientListView({collection: clientCollection});
    clientListView.render();

    var controller = new Controller();

    Backbone.history.start();
})

