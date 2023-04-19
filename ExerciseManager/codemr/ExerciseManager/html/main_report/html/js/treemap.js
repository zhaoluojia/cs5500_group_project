codemr.treemap=function(){codemr.treemap.paintCallBack=function(){u.style("fill",(function(t){return EQ_GET_COLOR(t)}))};var t,e,n=codemr.metricchart("metric-chart-in-treemap",classMetricSpec,classMetricValues),r=1200,a=620,i=d3.scale.linear().range([0,r]),l=d3.scale.linear().range([0,a]),c=(d3.scale.category20c(),d3.layout.treemap().round(!1).size([r,a]).sticky(!0).value((function(t){return t.value}))),o=d3.select("#treemap-chart-body").append("div").attr("class","treemapchart").style("width",r+"px").style("height",a+"px").append("svg:svg").attr("width",r).attr("height",a).append("svg:g").attr("transform","translate(.5,.5)");t=EQ_GET_DATA(),e=t;var d=c.nodes(t),s=o.selectAll("g").data(d).enter().append("svg:g").attr("class","cell").attr("transform",(function(t){return"translate("+t.x+","+t.y+")"})).on("click",(function(n){return h(e===n.parent?t:n.parent)})).on("mousemove",(function(t){return m(t)})).on("mouseout",(function(t){return f(t)})),u=s.append("svg:rect");u.attr("class","treerect").attr("width",(function(t){return t.dx>1?t.dx-1:0})).attr("height",(function(t){return t.dy>1?t.dy-1:0})).style("fill-opacity",(function(t){return t.children?0:.8})).style("fill",(function(t){return EQ_GET_COLOR(t)})).style("stroke",(function(t){return t.children?"#000000":"none"})).style("stroke-width",(function(t){return t.children?2:0})),s.append("svg:text").attr("class","treemaptext").attr("x",(function(t){return t.dx/2})).attr("y",(function(t){return t.dy/2})).attr("dy",".35em").attr("text-anchor","middle").text((function(t){return t.name})).style("opacity",(function(t){return t.w=this.getComputedTextLength(),t.h=this.clientHeight,0===t.depth||1===t.depth?0:0!==e.depth||t.children?t.h>t.dy?0:t.dx>t.w?1:0:0}));var p=c.nodes(t).filter((function(t){return 1===t.depth}));o.selectAll("g2").data(p).enter().append("svg:text").attr("class","packageNodesText").attr("transform",(function(t){return"translate("+t.x+","+t.y+")"})).attr("x",(function(t){return t.dx/2})).attr("y",(function(t){return t.dy/2})).attr("dy",".35em").attr("text-anchor","middle").text((function(t){return t.name})).style("opacity",(function(t){return t.w=this.getComputedTextLength(),t.h=this.clientHeight,0!==e.depth||t.children?t.h>t.dy?0:t.dx>t.w?1:0:0}));function h(t){var n=r/t.dx,c=a/t.dy;i.domain([t.x,t.x+t.dx]),l.domain([t.y,t.y+t.dy]),e=t;var d=o.selectAll("g.cell").transition().duration(d3.event.altKey?7500:750).attr("transform",(function(t){return"translate("+i(t.x)+","+l(t.y)+")"}));d.select("rect").attr("width",(function(t){return ret=n*t.dx-1,ret>0?ret:0})).attr("height",(function(t){var e=c*t.dy-1;return e>0?e:0})),d.select("text").attr("x",(function(t){return n*t.dx/2})).attr("y",(function(t){return c*t.dy/2})).style("opacity",(function(t){return 0!==e.depth||t.children?0===t.depth||1===t.depth||t.h>t.dy*c?0:n*t.dx>t.w?1:0:0})),o.selectAll(".packageNodesText").transition().duration(d3.event.altKey?7500:750).attr("transform",(function(t){return"translate("+i(t.x)+","+l(t.y)+")"})).style("opacity",(function(t){return 0!==e.depth||t.h>t.dy*c?0:n*t.dx>t.w?1:0})),d3.event.stopPropagation()}d3.select(window).on("click",(function(){h(t)}));var y=null,m=function(t){elem=document.getElementsByClassName("treemapchart"),boundingRect=elem[0].getBoundingClientRect(),d3.event.ctrlKey||d3.event.metaKey?n.setVisible(!0):n.setVisible(!1);var e=window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth,r=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight,a=d3.event.pageX,i=d3.event.pageY,l=n.getWidth();if(d3.event.pageX>boundingRect.left+600){var c=e-a;a<l&&(c=e-l),d3.select("#tooltip").style("right",c+"px"),d3.select("#tooltip").style("left","auto")}else{var o=a;a+l>e&&(o=e-l),d3.select("#tooltip").style("left",o+"px"),d3.select("#tooltip").style("right","auto")}d3.event.pageY>boundingRect.top+310?(d3.select("#tooltip").style("bottom",r-i+"px"),d3.select("#tooltip").style("top","auto")):(d3.select("#tooltip").style("top",i+"px"),d3.select("#tooltip").style("bottom","auto")),d3.select("#tooltip #parent").text(t.parent.name),d3.select("#tooltip #name").text(t.name),d3.select("#tooltip #metricvalue").text(EQ_getSelectedMetricName(t)+" : "+EQ_GET_METRICVALUE(t)),d3.select("#tooltip").classed("hidden",!1),y!==t&&t.metrics&&(n.updateMetricValues(EQ_convertMetricValues(t.metricvalues)),y=t,"function"==typeof updateSelectedElement&&updateSelectedElement(t.parent?t.parent.name:"",t.key))},f=function(t){d3.select("#tooltip").classed("hidden",!0)}};
