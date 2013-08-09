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
            // Default attributes for the Contact item
            defaults: function () {
                return {
                    clientId: '',
                    firstName: '',
                    surName: '',
                    lastName: '',
                    telephone: ''
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

            comparator: function (client) {
                return client.get("firstName");
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

            initialize: function () {
                this.model.on('all', this.render, this);
            },

            render: function () {
                $(this.el).html(this.template({client: this.model.toJSON()}));
                return this;
            }


        }
    );


    var Controller = Backbone.Router.extend({
        routes: {
            "": "list",
            "!/clients": "list",
            "!/client/:id": "details",
            "!/add": "add"
        },

        list: function () {
//            if (this.client) {
//                this.contactView.close();
//            }
            alert("inside");
            clientCollection.fetch();

        }
    });

    var clientCollection = new ClientCollection();
    clientCollection.reset(clientCollection.toJSON());

    var clientListView = new ClientListView({model: clientCollection});
    clientListView.render();

    var controller = new Controller();

    Backbone.history.start();
})