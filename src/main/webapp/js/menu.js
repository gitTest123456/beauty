/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 08.08.13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */


//Load application with JQuery when DOM is ready
$(function () {

    var Controller = Backbone.Router.extend({
        routes: {
            "!/clientsList": "list"
        },

        list: function () {
           window.location="/clients.html";
        }
    });

    var controller = new Controller();
    Backbone.history.start();
})

