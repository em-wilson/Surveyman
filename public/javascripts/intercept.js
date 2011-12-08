function SWIntercept(param)
{
    var defaults = {
      'type' : null,
      'direction' : null
    };
    this.settings = jQuery.extend(defaults, param);
}

SWIntercept.prototype.createOverlay = function()
{
    $overlay = jQuery("<div>&nbsp;</div>");
    $overlay.css('width', '100%');
    $overlay.css('height', '100%');
    $overlay.css('background-color', '#0f0');
    $overlay.css('opacity', '0.5');
    $overlay.css('position', 'fixed');
    $overlay.css('top', '0');
    $overlay.css('display', 'none');
    $overlay.click(function(){
        $(this).fadeOut('fast', function() {
		$(this).remove();
	});
    });
    jQuery("body").append($overlay);
    $overlay.fadeIn('fast');
    return $overlay;
}

SWIntercept.prototype.createDiv = function(holder,css)
{
    var $div = jQuery("<div>Intercept Div</div>");
    if ( this.settings.styles ) {
        for ( var key in this.settings.styles ) {
            var value = this.settings.styles[key];
            $div.css(key, value );
        }
    }
    $div.click(function(){return false;});
    $div.css('position', 'fixed');
    holder.append($div);
    var doc_height = $(document).height()-$("body").offset().top;
    var doc_width = $(document).width();
    var div_height = $div.height();
    var div_top = (doc_height / 2) - (div_height / 2);
    var div_left = (doc_width /2) - ($div.width() /2);
    $div.css('top', div_top + 'px');
    $div.css('left', div_left + 'px');
    if ( css ) {
	$div.css(css);
    }
    return $div;
};

SWIntercept.prototype.createSlideDiv = function($container)
{
    var doc_height = $(document).height()-$("body").offset().top;
    $div = this.createDiv($container, {
	top: doc_height + 'px'
    });
    $div.animate({top:'150px'});
    $div.click(function(){
	$(this).fadeOut(function() {
	    $(this).remove();
	});
    });
};

SWIntercept.prototype.run = function()
{
    switch ( this.settings.type )
    {
        case "fade":
            var holder = this.createOverlay();
            this.createDiv(holder);
            break;

        case "slide":
	    this.createSlideDiv($('body'));
            break;

        default:
            alert("Not implemented");
            break;
    }
};

function doFadein()
{
    var intercept = new SWIntercept({
        type: 'fade',
        styles: {
            'background-color' : '#ffffff',
            'width' : '150px',
            'height' : '150px',
        }
    });

    intercept.run();
}

function doSlideup()
{
    var intercept = new SWIntercept({
        type: 'slide',
        direction: 'up',
        styles: {
            'background-color' : '#ffffff',
            'width' : '150px',
            'height' : '150px',
        }
    });

    intercept.run();
}
