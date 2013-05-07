
Ext.define('view.exceptionViewHist', {
    extend: 'Ext.grid.Panel',
    flex:1,
    title:'Exception History',
    id: 'exh',
	plugins:[
        {
            ptype:'rowediting', //step 1
            //clicksToEdit:1
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
	            'loan_id', 'exc_type' , 'desc','date_inserted', 'action'
	            /*
	            {name:'client', type:'int'},
	            {name:'age', type:'int' },
	            {name:'active', type:'boolean' },
	            {name:'amount', type:'float' },
	            'paydate'*/
	        ]
	    });
	},
	buildStore:function(){  //defining the client`s store
		return Ext.create('Ext.data.Store', {
			model:'Client',
			autoLoad: true,
		    pageSize: 20,
		    proxy: {
		        type: 'ajax',
		        url: 'getExceptionHist',
		        //extraParams: {
		          //  loan_id: 1
		        //},
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
	
	    });
	},
	buildColumns : function(){
		return [
            {
                text:'Loan Id',
                flex:1,
                dataIndex:'loan_id',
            },
            {
                text:'Description',
                flex:1,
                dataIndex:'desc',
            },
            {
                text:'Exception Type',
                flex:1,
                dataIndex:'exc_type',
            },
            {
                text:'Date Inserted',
                flex:1,
                dataIndex:'date_inserted',
            },
            {
                text:'Action',
                flex:1,
                dataIndex:'action',
            }
      
        ];
	}
});