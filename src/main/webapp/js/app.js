$(function() {
    var pipesWeb, pipesData;
            
    var pipes = AeroGear.Pipeline([
    {
        name: "pipes",
        settings: {
            url: "./pipes"
        }
    }
    ]),
                
    Pipes = pipes.pipes[ "pipes" ];
            
    showPipes = function() {
        pipesData = Pipes.read();
        pipesWeb = $.get("./pipes");
        var pipesTemplate = $.get("js/templates/pipes.mustache");
        $.when(pipesWeb, pipesData, pipesTemplate ).then(function(web, data, template) {
            console.log(web)
            console.log(data[0] )
            console.log(template)
                
            $('#app').html(web[0] + " " + Mustache.render(template[0], {
                "pipes":data[0]
                }));
            $('#appForm').on('submit', function(event) {
                console.log('saving')
                event.preventDefault();
                var saveOp = Pipes.save($(this).serializeObject());
                $.when(saveOp).then(function(){
                    showPipes()
                    });
                return false;
            });
                
        });
        
    };    

    $('nav #pipes').on("click", showPipes);


    // Serializes a form to a JavaScript Object
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each( a, function() {
            if ( o[ this.name ] ) {
                if ( !o[ this.name ].push ) {
                    o[ this.name ] = [ o[ this.name ] ];
                }
                o[ this.name ].push( this.value || '' );
            } else {
                o[ this.name ] = this.value || '';
            }
        });
        return o;
    };
});