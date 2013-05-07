/**
 * @class MyApp.view.clients.Form
 * @extends Ext.form.Panel
 * @author Crysfel Villa <crysfel@bleext.com>
 *
 * The client form
 */

Ext.define('view.Form',{
    extend      : 'Ext.form.Panel',
    alias       : 'widget.exceptionform',
    layout      :  'column',
    title		: 'Enter search criteria',
    flex: .5,
    bodyPadding	: 5,
    //labelWidth: 200,
   // layout: hbox,
    defaultType	: 'textfield',

    initComponent   : function(){
        var me = this;

        me.items = me.buildItems();

        //me.dockedItems = me.buildToolbars();
        
        me.callParent();
    },
     
    buildItems      : function(){
        return [{
            fieldLabel  : 'Loan Id',
            name        : 'loan_id',
            columnWidth : .1,
            labelWidth: 50,
        },{
            fieldLabel  : 'Exception Type',
            name        : 'exc_type',
            columnWidth : .1,
            //labelWidth: 50,
        },{
            fieldLabel  : 'Descrription',
            name        : 'desc',
            columnWidth : .2,
            labelWidth: 100,
        },
        {
            fieldLabel  : 'Search',
            name        : 'search',
            id          : 'btn',
            width : "30px",
            labelWidth: 30,
            xtype: 'button',
            text: "Search",
            handler:function getData(id){ 
            		//var win = this.up('window');
                    var form = this.up('form').getForm();
            		var loan_id = form.findField('loan_id').getValue();
            		var exc_type = form.findField('exc_type').getValue();
            		var desc = form.findField('desc').getValue();
            		var str = "Loan Id= " + loan_id  + " Exc Type = " + exc_type + " desc = " + desc;
            		alert(str);
            	},
            
            
        }
        
        ];
    },
    

    buildToolbars    : function(){
        return [{
            xtype   : 'toolbar',
            docked  : 'top',
            items   : [{
                text    : 'New',
                iconCls : 'new-icon'
            },{
                text    : 'Save',
                iconCls : 'save-icon'
            },{
                text    : 'Delete',
                iconCls : 'delete-icon'
            }]
        }];
    }
 
});