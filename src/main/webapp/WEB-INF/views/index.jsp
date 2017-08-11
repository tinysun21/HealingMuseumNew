<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

	<!-- 
            <h1>Fullscreen Image <span>Blur</span> Effect with HTML5</h1>
   -->         
            <div id="bx-wrapper" class="bx-wrapper">
                <div class="bx-loading">
                    <span>Loading...</span>
                </div>
				
				<div class="bx-content">
					<h2>Healing Museum</h2>
				</div>
				<!--
				<div class="bx-content">
				 
					<c:if test="">
					<h3 onclick="location.href='fullscreen_page.do?theme=1'" alt="입장하기">Enter</h3>
					</c:if>
					
					<c:if test="">
					<h3 onclick="location.href='fullscreen_page.do?theme=2'" alt="입장하기">Enter</h3>
					</c:if>
					
					<c:if test="">
					<h3 onclick="location.href='fullscreen_page.do?theme=3'" alt="입장하기">Enter</h3>
					</c:if>
					
				</div>
				 -->
				<div class="bx-thumbs">
                    <a href="#" class="bx-thumbs-current" style="background-image:url(images/thumbs/1.jpg)"></a>
                    <a href="#" style="background-image:url(images/thumbs/2.jpg)"></a>
                    <a href="#" style="background-image:url(images/thumbs/3.jpg)"></a>
                </div>
				
                <div class="bx-container">
                    <img src="images/large/1.jpg" alt="image01" title="Healing Museum"/>	
                    <img src="images/large/2.jpg" alt="image02" title="Healing Museum"/>
                    <img src="images/large/3.jpg" alt="image03" title="Healing Museum"/>
                </div>
				
                <div class="bx-overlay"></div>
            </div>
        </div>
        
        <script type="text/javascript" src="js/tik.js"></script>
        <script type="text/javascript">
        	
        var currentOS;
        var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
         
        if (mobile) {
        	// 유저에이전트를 불러와서 OS를 구분합니다.
        	var userAgent = navigator.userAgent.toLowerCase();
        	if (userAgent.search("android") > -1)
        		currentOS = "android";
        	else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1)
        				|| (userAgent.search("ipad") > -1))
        		currentOS = "ios";
        	else
        		currentOS = "else";
        } else {
        	// 모바일이 아닐 때
        	currentOS = "nomobile";
        }
        $(document).ready(function(){
        	if(currentOS != "nomobile"){
        		alert("본 서비스는 PC환경에서만 이용이 가능합니다. \n모바일 버전은 현재 준비중에 있습니다. 이용에 불편을 드려 죄송합니다.");
        	}
        });
        
            $(function() {
				
                var BlurBGImage	= (function() {
					
                    var $bxWrapper			= $('#bx-wrapper'),
                    // loading status to show while preloading images
                    $bxLoading			= $bxWrapper.find('div.bx-loading'),
                    // container for the bg images and respective canvas
                    $bxContainer		= $bxWrapper.find('div.bx-container'),
                    // the bg images we are gonna use
                    $bxImgs				= $bxContainer.children('img'),
                    // total number of bg images
                    bxImgsCount			= $bxImgs.length,
                    // the thumb elements
                    $thumbs				= $bxWrapper.find('div.bx-thumbs > a').hide(),
                    // the title for the current image
                    $title				= $bxWrapper.find('h2:first'),
                    // current image's index
                    current				= 0,
                    // variation to show the image:
                    // (1) - blurs the current one, fades out and shows the next image
                    // (2) - blurs the current one, fades out, shows the next one (but initially blurred)
                    // speed is the speed of the animation
                    // blur Factor is the factor used in the StackBlur script
                    animOptions			= { speed : 700, variation : 1, blurFactor : 10 },
                    // control if currently animating
                    isAnim				= false,
                    // check if canvas is supported
                    supportCanvas 		= Modernizr.canvas,
					
                    // init function
                    init				= function() {
							
                        // preload all images and respective canvas
                        var loaded = 0;
							
                        $bxImgs.each( function(i) {
								
                            var $bximg	= $(this);
								
                            // save the position of the image in data-pos
                            $('<img data-pos="' + $bximg.index() + '"/>').load(function() {
									
                                var $img	= $(this),
                                // size of image to be fullscreen and centered
                                dim		= getImageDim( $img.attr('src') ),
                                pos		= $img.data( 'pos' );
									
                                // add the canvas to the DOM
                                $.when( createCanvas( pos, dim ) ).done( function() {
											
                                    ++loaded;
										
                                    // all images and canvas loaded
                                    if( loaded === bxImgsCount ) {
											
                                        // show thumbs
                                        $thumbs.fadeIn();
											
                                        // apply style for bg image and canvas
                                        centerImageCanvas();
											
                                        // hide loading status
                                        $bxLoading.hide();
											
                                        // initialize events
                                        initEvents();
										
                                    }
										
                                });
								
                            }).attr( 'src', $bximg.attr('src') );
							
                        });
						
                    },
                    // creates the blurred canvas image
                    createCanvas		= function( pos, dim ) {
							
                        return $.Deferred( function(dfd) {
								
                            // if canvas not supported return
                            if( !supportCanvas ) {
                                dfd.resolve();
                                return false;
                            }	
								
                            // create the canvas element:
                            // size and position will be the same like the fullscreen image
                            var $img	= $bxImgs.eq( pos ),
                            imgW	= dim.width,
                            imgH	= dim.height,
                            imgL	= dim.left,
                            imgT	= dim.top,
									
                            canvas	= document.createElement('canvas');
								
                            canvas.className	= 'bx-canvas';
                            canvas.width 		= imgW;
                            canvas.height 		= imgH;
                            canvas.style.width  = imgW + 'px';
                            canvas.style.height = imgH + 'px';
                            canvas.style.left	= imgL + 'px';
                            canvas.style.top	= imgT + 'px';
                            canvas.style.visibility = 'hidden';
                            // save position of canvas to know which image this is linked to
                            canvas.setAttribute('data-pos', pos);
                            // append the canvas to the same container where the images are
                            $bxContainer.append( canvas );
                            // blur it using the StackBlur script
                            stackBlurImage( $img.get(0), dim, canvas, animOptions.blurFactor, false, dfd.resolve );
							
                        }).promise();							
								
                    },
                    // gets the image size and position in order to make it fullscreen and centered.
                    getImageDim			= function( img ) {
							
                        var $img    = new Image();
							
                        $img.src    = img;
							
                        var $win	= $( window ),
                        w_w		= $win.width(),
                        w_h		= $win.height(),
                        r_w		= w_h / w_w,
                        i_w		= $img.width,
                        i_h		= $img.height,
                        r_i		= i_h / i_w,
                        new_w, new_h, new_left, new_top;
									
                        if( r_w > r_i ) {
								
                            new_h	= w_h;
                            new_w	= w_h / r_i;
							
                        }
                        else {
							
                            new_h	= w_w * r_i;
                            new_w	= w_w;
							
                        }
									
                        return {
                            width	: new_w,
                            height	: new_h,
                            left	: ( w_w - new_w ) / 2,
                            top		: ( w_h - new_h ) / 2
                        };
						
                    },
                    // initialize the events
                    initEvents			= function() {
							
                        $( window ).on('resize.BlurBGImage', function( event ) {
								
                            // apply style for bg image and canvas
                            centerImageCanvas();
                            return false;
								
                        });
							
                        // clicking on a thumb shows the respective bg image
                        $thumbs.on('click.BlurBGImage', function( event ) {
								
                            var $thumb	= $(this),
                            pos		= $thumb.index();
								
                            if( !isAnim && pos !== current ) {
									
                                $thumbs.removeClass('bx-thumbs-current');
                                $thumb.addClass('bx-thumbs-current');
                                isAnim = true;
                                // show the bg image
                                showImage( pos );
								
                            }
								
                            return false;
								
                        });
						
                    },
                    // apply style for bg image and canvas
                    centerImageCanvas	= function() {
							
                        $bxImgs.each( function(i) {
								
                            var $bximg	= $(this),
                            dim			= getImageDim( $bximg.attr('src') ),
                            $currCanvas	= $bxContainer.children('canvas[data-pos=' + $bximg.index() + ']'),
                            styleCSS	= {
                                width	: dim.width,
                                height	: dim.height,
                                left	: dim.left,
                                top		: dim.top
                            };	
								
                            $bximg.css( styleCSS );
								
                            if( supportCanvas )
                                $currCanvas.css( styleCSS );
									
                            if( i === current )	
                                $bximg.show();
								
                        });
							
                    },
                    
                    // shows the image at position "pos"
                    showImage			= function( pos ) {
						
                        // current image 
                        var $bxImage		= $bxImgs.eq( current );
                        // current canvas
                        $bxCanvas		= $bxContainer.children('canvas[data-pos=' + $bxImage.index() + ']'),
                        // next image to show
                        $bxNextImage	= $bxImgs.eq( pos ),
                        // next canvas to show
                        $bxNextCanvas	= $bxContainer.children('canvas[data-pos='+$bxNextImage.index()+']');
							
                        // if canvas is supported
                        if( supportCanvas ) {
							
                            $.when( $title.fadeOut() ).done( function() {
								
                                $title.text( $bxNextImage.attr('title') );
									
                            });
							
                            $bxCanvas.css( 'z-index', 100 ).css('visibility','visible');
								
                            $.when( $bxImage.fadeOut( animOptions.speed ) ).done( function() {
									
                                switch( animOptions.variation ) {
									
                                    case 1 	:
                                        $title.fadeIn( animOptions.speed );
                                        $.when( $bxNextImage.fadeIn( animOptions.speed ) ).done( function() {
										
                                            $bxCanvas.css( 'z-index', 1 ).css('visibility','hidden');
                                            current = pos;
                                            $bxNextCanvas.css('visibility','hidden');
                                            isAnim 	= false;
											
                                        });
                                        break;
                                    case 2	:
                                        $bxNextCanvas.css('visibility','visible');
											
                                        $.when( $bxCanvas.fadeOut( animOptions.speed * 1.5 ) ).done( function() {
											
                                            $(this).css({
                                                'z-index' 		: 1,
                                                'visibility'	: 'hidden'
                                            }).show();
												
                                            $title.fadeIn( animOptions.speed );
												
                                            $.when( $bxNextImage.fadeIn( animOptions.speed ) ).done( function() {
													
                                                current = pos;
                                                $bxNextCanvas.css('visibility','hidden');
                                                isAnim 	= false;
												
                                            });
												
                                        });
                                        break;
									
                                };
									
                            });
							
                        }
                        // if canvas is not shown just work with the bg images
                        else {
								
                            $title.hide().text( $bxNextImage.attr('title') ).fadeIn( animOptions.speed );
                            $.when( $bxNextImage.css( 'z-index', 102 ).fadeIn( animOptions.speed ) ).done( function() {
									
                                current = pos;
                                $bxImage.hide();
                                $(this).css( 'z-index', 101 );
                                isAnim = false;
								
                            });
							
                        }
						
                    };
						
                    return {
                        init	: init
                    };
				
                })();
				
                // call the init function
                BlurBGImage.init();
				
            });
        </script>
    </body>
</html>