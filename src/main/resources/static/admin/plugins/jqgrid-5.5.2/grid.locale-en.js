/**
 * jqGrid Chinese Translation
 * 咖啡兔 yanhonglei@gmail.com
 * http://www.kafeitu.me
 *
 * 花岗岩 marbleqi@163.com
 *
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
/*global jQuery, define */
(function( factory ) {
	"use strict";
	if ( typeof define === "function" && define.amd ) {
		// AMD. Register as an anonymous module.
		define([
			"jquery",
			"../grid.base"
		], factory );
	} else {
		// Browser globals
		factory( jQuery );
	}
}(function( $ ) {

$.jgrid = $.jgrid || {};
if(!$.jgrid.hasOwnProperty("regional")) {
	$.jgrid.regional = [];
}
$.jgrid.regional["en"] = {
    defaults: {
        recordtext: "Article {0} to Article {1}\u3000 A total of {2} articles", // There is a full-width space before the common word
        emptyrecords: "No records!",
        loadtext: "Loading...",
        savetext: "Saving...",
        pgtext: "Page {0}\u3000 A total of {1} pages",
        pgfirst: "First page",
        pglast: "Last Page",
        pgnext: "Next Page",
        pgprev: "Previous page",
        pgrecs: "Number of records per page",
        showhide: "Switch to expand and collapse tables",
// mobile
        pagerCaption: "Form::Page Settings",
        pageText: "Page:",
        recordPage: "Number of records per page",
        nomorerecs: "No more records...",
        scrollPullup: "Load more...",
        scrollPulldown: "Refresh...",
        scrollRefresh: "Scroll to refresh..."
    },
    search : {
        caption: "Search...",
        Find: "Find",
        Reset: "Reset",
        odata: [{ oper:'eq', text:'equal to \u3000\u3000'},{ oper:'ne', text:'not equal to \u3000'},{ oper:'lt', text:'less than\ u3000\u3000'},{ oper:'le', text:'less than or equal to'},{ oper:'gt', text:'greater than\u3000\u3000'},{ oper:'ge', text:'greater than Equal to'},{ oper:'bw', text:'begins with'},{ oper:'bn', text:'is not at the beginning'},{ oper:'in', text:'belongs to \u3000\u3000' },{ oper:'ni', text:'does not belong'},{ oper:'ew', text:'end is'},{ oper:'en', text:'end is not'},{ oper: 'cn', text:'contains \u3000\u3000'},{ oper:'nc', text:'does not contain'},{ oper:'nu', text:'is empty'},{ oper:'nn ', text:'not empty'}, {oper:'bt', text:'interval'}],
        groupOps: [{op: "AND", text: "Satisfy all conditions" }, {op: "OR", text: "Satisfy any conditions"} ],
        operandTitle: "Click to search.",
        resetTitle: "Reset Search Criteria",
        addsubgrup: "Add Condition Group",
        addrule: "Add Condition",
        delgroup: "Delete Condition Group",
        delrule: "Delete condition"
    },
    edit : {
        addCaption: "Add Record",
        editCaption: "Edit Record",
        bSubmit: "Submit",
        bCancel: "Cancel",
        bClose: "Close",
        saveData: "The data has been modified, do you want to save it?",
        bYes: "Yes",
        bNo: "No",
        bExit: "Cancel",
        msg: {
            required:"This field is required",
            number:"Please enter a valid number",
            minValue: "The input value must be greater than or equal to",
            maxValue: "The input value must be less than or equal to",
            email: "This is not a valid e-mail address",
            integer: "Please enter a valid integer",
            date: "Please enter a valid time",
            url: "Invalid URL. The prefix must be ('http://' or'https://')",
            nodefined: "Undefined!",
            novalue: "Return value is required!",
            customarray: "Custom functions need to return an array!",
            customfcheck: "Must have a custom function!"
        }
    },
    view: {
        caption: "View Record",
        bClose: "Close"
    },
    del: {
        caption: "Delete",
        msg: "Delete selected records?",
        bSubmit: "Delete",
        bCancel: "Cancel"
    },
    nav: {
        edittext: "",
        edittitle: "Edit the selected record",
        addtext:"",
        addtitle: "Add new record",
        deltext: "",
        deltitle: "Delete selected record",
        searchtext: "",
        searchtitle: "Search",
        refreshtext: "",
        refreshtitle: "Refresh Table",
        alertcap: "Attention",
        alerttext: "Please select a record",
        viewtext: "",
        viewtitle: "View the selected record",
        savetext: "",
        savetitle: "Save Record",
        canceltext: "",
        canceltitle: "Cancel edit record",
        selectcaption: "Operation..."
    },
    col: {
        caption: "Select column",
        bSubmit: "OK",
        bCancel: "Cancel"
    },
    errors: {
        errcap: "Error",
        nourl: "No url is set",
        norecords: "There are no records to be processed",
        model: "colNames and colModel have different lengths!"
    },
    formatter: {
        integer: {thousandsSeparator: ",", defaultValue: '0'},
        number: {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
        currency: {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
        date: {
            dayNames: [
                "日", "one", "two", "three", "four", "five", "six",
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            ],
            monthNames: [
                "One", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
            ],
            AmPm: ["am","pm","AM","PM"],
            S: function (j) {return j <11 || j> 13? ['St','nd','rd','th'][Math.min((j-1)% 10, 3)] :'th';},
            srcformat:'Y-m-d',
            newformat:'Y-m-d',
            parseRe: /[#%\\\/:_;.,\t\s-]/,
            masks: {
                // see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
                // and see http://docs.jquery.com/UI/Datepicker/formatDate
                // and https://github.com/jquery/globalize#dates for alternative formats used frequently
                // one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
                // information about date, time, numbers and currency formats used in different countries
                // one should just convert the information in PHP format
                ISO8601Long:"Y-m-d H:i:s",
                ISO8601Short: "Y-m-d",
                // short date:
                // n-Numeric representation of a month, without leading zeros
                // j-Day of the month without leading zeros
                // Y-A full numeric representation of a year, 4 digits
                // example: 3/1/2012 which means 1 March 2012
                ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
                // long date:
                // l-A full textual representation of the day of the week
                // F-A full textual representation of a month
                // d-Day of the month, 2 digits with leading zeros
                // Y-A full numeric representation of a year, 4 digits
                LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
                // long date with long time:
                // l-A full textual representation of the day of the week
                // F-A full textual representation of a month
                // d-Day of the month, 2 digits with leading zeros
                // Y-A full numeric representation of a year, 4 digits
                // g-12-hour format of an hour without leading zeros
                // i-Minutes with leading zeros
                // s-Seconds, with leading zeros
                // A-Uppercase Ante meridiem and Post meridiem (AM or PM)
                FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
                // month day:
                // F-A full textual representation of a month
                // d-Day of the month, 2 digits with leading zeros
                MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
                // short time (without seconds)
                // g-12-hour format of an hour without leading zeros
                // i-Minutes with leading zeros
                // A-Uppercase Ante meridiem and Post meridiem (AM or PM)
                ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
                // long time (with seconds)
                // g-12-hour format of an hour without leading zeros
                // i-Minutes with leading zeros
                // s-Seconds, with leading zeros
                // A-Uppercase Ante meridiem and Post meridiem (AM or PM)
                LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
                SortableDateTime: "Y-m-d\\TH:i:s",
                UniversalSortableDateTime: "Y-m-d H:i:sO",
                // month with year
                // Y-A full numeric representation of a year, 4 digits
                // F-A full textual representation of a month
                YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
            },
            reformatAfterEdit: false,
            userLocalTime: false
        },
        baseLinkUrl:'',
        showAction:'',
        target:'',
        checkbox: {disabled:true},
        idName:'id'
    },
    colmenu: {
        sortasc: "Sort ascending",
        sortdesc: "Sort in descending order",
        columns: "Columns",
        filter: "Filter",
        grouping: "Classification",
        ungrouping: "Ungrouping",
        searchTitle: "Search:",
        freeze: "Freeze",
        unfreeze: "Unfreeze",
        reorder: "Reorder"
    }
};
}));