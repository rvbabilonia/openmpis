
// define the namespaces
jmaki.namespace("jmaki.widgets.Ext.menu");

jmaki.widgets.Ext.menu.Widget = function(wargs) {
    
    var container = document.getElementById(wargs.uuid);
    var self = this;

    var topic = "/jmaki/menu";

    this.menubar;

    if (wargs.args && wargs.args.topic) {
        topic = wargs.args.topic;
    }
    
    // pull in the arguments
    if (wargs.value) {
        menu = wargs.value.menu;
        rows = wargs.value.rows;
        init();
    } else if (wargs.service) {
            jmaki.doAjax({url: wargs.service, callback: function(req) {
        if (req.readyState == 4) {
            if (req.status == 200) {
              var data = eval('(' + req.responseText + ')');
              menu = data.menu;
              init();
          }
        }
      }});
    } else {
        menu = [
        {label: 'Some Topic',
            menu: [
                {label:'Some Item', url:'1.jsp'},
                {label:'Some Item 2', url:'2.jsp'}
                ]},

        {label: 'Some Other Topic',

            menu: [
                {label:'Some Other Item', url:'1.jsp'},
                {label:'Some Other Item 2', url:'2.jsp'}
                ]}
        ];
        init();
    }
    
    function showURL (largs) {
        jmaki.publish(topic, largs.url);
    }
    
    function init() {
        menubar = new Ext.Toolbar(wargs.uuid);
        for (var i = 0; i < menu.length; i++) {
            var items = [];
            for (var ii=0; ii < menu[i].menu.length; ii++){
                items.push({text : menu[i].menu[ii].label, handler : showURL, url : menu[i].menu[ii].url});
            }
            menubar.add(new Ext.Toolbar.MenuButton({
                cls: 'x-btn-text-icon bmenu',
                        text : menu[i].label,
                        id : wargs.uuid + '_' + i,
                        menu : new Ext.menu.Menu({items : items})}));
            if (i < menu.length -1 ) menubar.addSeparator();
        }
        
    }
}