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
    var Employer = Backbone.Model.extend(
        {
            url: "/beauty/employers/add",
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
    /**********************************************************************************
     **                     Views
     **********************************************************************************/
    var EmployerListView = Backbone.View.extend(
        {

            template: _.template($('#employer-list-template').html()),
            el: $("#employer-list-block"),
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
                $(this.el).html(this.template({employer: this.collection}));
                return this;
            },

            afterRender: function () {
            }  }


    ); //Backbone.Model.extend end


    // The collection of employers
    var EmployerCollection = Backbone.Collection.extend(
        {
            model: Employer,
            url: "/beauty/employers",
            parse: function (response) {
                console.log('Parsing list of the employers:' + response);
                employersCollection = response;
                employersListView.collection = employersCollection;
                employersListView.render();
                return response;
            }
        }
    );


    var EmployerView = Backbone.View.extend(
        {

            template: _.template($('#employer-details-template').html()),
            el: $("#employer-details-block"), // DOM element with contact details,

            events: {
                "click  #form-btn-remove": "deleteEmployer",
                "click #form-btn-add": "addEmployer"
            },
            render: function () {
                $(this.el).html(this.template({
                    employer: this.model
                }))
            },
            deleteEmployer: function (e) {
                e.preventDefault();
                if (jQuery("#employer-details-block").valid()) {
                    $.ajax({
                        type: "post",
                        url: "/beauty/employers/delete", //your valid url
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
            addEmployer: function (e) {
                if (jQuery("#employer-details-block").valid())
                {
                    var firstName = $("#inputEmployerName").val();
                    var surName = $('#inputEmployerSurName').val();
                    var lastName = $('#inputEmployerLastName').val();
                    var telephone = $('#inputEmployerPhone').val();
                    var address = $('#inputEmployerAddress').val();
                    var birthday = $('#inputEmployerBirthday').val();
                    var separation = $('#inputEmployerSeparation').val();;
                    var id = $('#inputEmployerId').val();

                    var newEmployer = new Employer(
                        {   "employerId": id,
                            "firstName": firstName,
                            "surName": surName,
                            "lastName": lastName,
                            "telephone": telephone,
                            "address": address,
                            "birthday": birthday,
                            "separation": employersCollection[0].separation}).save({}, {
                            wait: true,
                            success: function (model, response) {
                                window.location = "/employers.html";
                            },
                            error: function (model, error) {
                                window.location = "/employers.html";
                            }
                        });
                } else {
                    $.fancybox.open($("#dialog"));
                }
            }
        }
    );


    var EmployerController = Backbone.Router.extend({
        routes: {
            "!/": "list",
            "/": "list",
            "": "list",
            "!/employer_": "list",
            "!/delete_/:itemIndex": "delete_",
            "!/edit/:itemIndex": "edit",
            "!/back": "back"
        },

        list: function () {
            employersCollection.fetch();
        },

        edit: function (itemIndex) {
            employerView.model = employersCollection[itemIndex];
            employerView.render();
        },
        back: function () {
            window.location = "/"
        }
    });

    var employersCollection = new EmployerCollection();
    var employersListView = new EmployerListView({collection: employersCollection});
    employersListView.render();
    var curEmployer = new Employer();
    var employerView = new EmployerView({model: curEmployer});
    employerView.render();
    var controller = new EmployerController();
    var timer_pagination = null;
    Backbone.history.start();
})

