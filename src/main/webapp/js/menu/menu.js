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
            "!/clientsList": "clientsList",
            "!/employersList": "employersList",
            "!/servicesList": "servicesList",
            "!/statisticsList": "statisticsList",
            "!/galleryLink": "galleryLink"
        },

        clientsList: function () {
            window.location = "/clients.html";
        },

        employersList: function () {
            window.location = "/employers.html"
        },

        servicesList: function () {
            window.location = "/services.html"
        },

        statisticsList: function () {
            window.location = "/statistic.html"
        },

        galleryLink: function () {
            window.location = "/gallery.html"
        }
    });

    var controller = new Controller();
    Backbone.history.start();
})

