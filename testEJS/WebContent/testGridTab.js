<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Exceptions</title>
    <link rel="stylesheet" href="http://cdn.sencha.io/ext-4.1.0-gpl/resources/css/ext-all.css" />
</head>
<body style="padding: 50px">
</body>
</html>
<script type="text/javascript" charset="utf-8" src="http://cdn.sencha.io/ext-4.1.0-gpl/ext-all-debug.js"></script>
<script type="text/javascript" src="view/exceptionView.js"></script>
<script type="text/javascript" src="view/exceptionForm.js"></script>
<script type="text/javascript">

Ext.onReady(function(){
    Ext.define('Book',{
        extend: 'Ext.data.Model',
        fields: [
            // set up the fields mapping into the xml doc
            // The first needs mapping, the others are very basic
            {name: 'Author', mapping: 'ItemAttributes > Author'},
            'Title', 'Manufacturer', 'ProductGroup'
        ]
    });

    // create the Data Store
    var store1 = Ext.create('Ext.data.Store', {
        model: 'Book',
        autoLoad: true,
        proxy: {
            // load using HTTP
            type: 'ajax',
            url: 'sheldon-1.xml',
            // the return will be XML, so lets set up a reader
            reader: {
                type: 'xml',
                // records will have an "Item" tag
                record: 'Item',
                idProperty: 'ASIN',
                totalRecords: '@total'
            }
        }
    });

    // create the Data Store
    var store2 = Ext.create('Ext.data.Store', {
        model: 'Book',
        autoLoad: true,
        proxy: {
            // load using HTTP
            type: 'ajax',
            url: 'sheldon-2.xml',
            // the return will be XML, so lets set up a reader
            reader: {
                type: 'xml',
                // records will have an "Item" tag
                record: 'Item',
                idProperty: 'ASIN',
                totalRecords: '@total'
            }
        }
    });

    // create the grid
    var grid1 = Ext.create('Ext.grid.Panel', {
        store: store1,
        columns: [
            {text: "Author", flex: 1, dataIndex: 'Author', sortable: true},
            {text: "Title", width: 180, dataIndex: 'Title', sortable: true},
            {text: "Manufacturer", width: 115, dataIndex: 'Manufacturer', sortable: true},
            {text: "Product Group", width: 100, dataIndex: 'ProductGroup', sortable: true}
        ],
        title: 'Grid1'
    });

    // create the grid
    var grid2 = Ext.create('Ext.grid.Panel', {
        store: store2,
        columns: [
            {text: "Author", flex: 1, dataIndex: 'Author', sortable: true},
            {text: "Title", width: 180, dataIndex: 'Title', sortable: true},
            {text: "Manufacturer", width: 115, dataIndex: 'Manufacturer', sortable: true},
            {text: "Product Group", width: 100, dataIndex: 'ProductGroup', sortable: true}
        ],
        title: 'Grid2'
    });

    Ext.create('Ext.TabPanel', {
        renderTo: Ext.getBody(),
        items: [
            grid1, grid2
        ]
    });
});
</script?