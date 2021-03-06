/**
 * @class MyApp.view.GridRowEditingPlugin
 * @extends Ext.grid.Panel
 * @author Armando Gonzalez <iam@armando.mx>
 * This the grid row editing example.
 */
Ext.define('view.fundingExcepView', {
    extend: 'Ext.grid.Panel',
    //flex:2,
    width:1550,
    height:350,
    title:'Exception',
	plugins:[
        {
            ptype:'cellediting', //step 1
            clicksToEdit:1
        }
    ],
	initComponent: function() {
		var me = this;
	    me.createModel();
		me.columns = me.buildColumns();
		me.store =  me.buildStore();
	   this.callParent(arguments);
	},
	createModel:function(){ //The definition of the client model
	    Ext.define('Client', {
	        extend:'Ext.data.Model',
	        fields:[
	            'loan_id', 'exc_type' , 'desc',/* 'country',
	            {name:'client', type:'int'},
	            {name:'age', type:'int' },
	            {name:'active', type:'boolean' },
	            {name:'amount', type:'float' },
	            'paydate'*/
	        ]
	    });
	},
	listeners : {
	    itemclick: function(dv, record, item, index, e) {
	    	//alert(record.get('loan_id'));
	    	var loan_id = record.get('loan_id');
	    	//Ext.getCmp('exh').getView().refresh();
	    	var exhGrid = Ext.getCmp('exh');
	    	//exhGrid.getStore().load();
	    	exhGrid.getStore().proxy.extraParams = { loan_id:loan_id};
	    	Ext.getCmp('exh').getStore().load();
	    	
	    	//var exh = Ext.getCmp('exh');
	    	//exh.reload();
	        
	    }
	},
	buildStore:function(){  //defining the client`s store
		return Ext.create('Ext.data.Store', {
			model:'Client',
			autoLoad: true,
		    pageSize: 20,
		    proxy: {
		        type: 'ajax',
		        url: 'getExceptions',
		        /*extraParams: {
		            store_id: 1
		        },*/
		        reader: {
		            type: 'json',
		            totalProperty: 'totalCount',
		            root: 'data',
		            successProperty: 'success'
		        },
		     },
		    
		     listeners: {
		         load : function(store) {
		             //if need something to do after the store loads
		         } 
		     }
			/*
	        model:'Client',
	        data:[
	            {
	                client:1,
	                name:'David',
	                lastname:'Lee', age:24,
	                email:'david@email.com',
	                country:'China',
	                paydate:'08/08/2012',
	                amount:120.5,
	                active:true
	            },
	            {
	                client:2,
	                name:'Lisa',
	                lastname:'Brown',
	                age:25,
	                email:'lisa@email.com',
	                country:'Japan',
	                paydate:'08/08/2012',
	                amount:120.5, active:false
	            },
	            {
	                client:3,
	                name:'Armando',
	                lastname:'Gonzalez',
	                age:30,
	                email:'armando@email.com',
	                country:'Mexico',
	                paydate:'08/28/2012',
	                amount:120,
	                active:true
	            },
	            {
	                client:4,
	                name:'Mike',
	                lastname:'Chang',
	                age:27,
	                email:'mike@email.com',
	                country:'Japan',
	                paydate:'08/08/2012',
	                amount:12.5456,
	                active:false
	            },
	            {
	                client:5,
	                name:'Kevin',
	                lastname:'Smith',
	                age:28,
	                email:'kevin@email.com',
	                country:'Mexico',
	                paydate:'08/08/2012',
	                amount:54.5,
	                active:true
	            }
	        ]*/
	    });
	},
	buildColumns : function(){
		return [
            {
                text:'Loan Id',
                //xtype:'templatecolumn',
                flex:1,
                dataIndex:'loan_id',
                //tpl:'<b>{name} {lastname} </b> (age: {age})</br>{email}</br>'
            },
            {
                text:'Description',
                //xtype:'templatecolumn',
                flex:1,
                dataIndex:'desc',
                //tpl:'<b>{name} {lastname} </b> (age: {age})</br>{email}</br>'
            },
            {
                text:'Exception Type',
                //xtype:'templatecolumn',
                flex:1,
                dataIndex:'exc_type',
                //tpl:'<b>{name} {lastname} </b> (age: {age})</br>{email}</br>'
            },
            
            /*
            {
                text:'Country',
                dataIndex:'country',
                editor:{ //step 2
                    xtype:'combo',
                    allowBlank:false,
                    displayField:'name',
                    store:Ext.create('Ext.data.Store', {
                        fields:['name'],
                        data:[
                            {"name":"China"},
                            {"name":"Australia"},
                            {"name":"Mexico"},
                            {"name":"Japan"},
                            {"name":"Usa"}
                        ]
                    })
                }
            },
            {
                text:'Pay Date',
                dataIndex:'paydate',
                xtype:'datecolumn',
                format:'m/d/Y',
                editor:{ //step 3
                    xtype:'datefield',
                    allowBlank:false
                }
            }*/
        ];
	}
});