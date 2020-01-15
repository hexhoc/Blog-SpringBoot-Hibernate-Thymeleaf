/*
Create a blog directory,
id represents the id of the div container that contains the body of the blog post,
mt and st represent the label names of the main title and subtitle respectively (such as H2, H3, uppercase or lowercase can be used!),
interval represents the speed of movement
*/
function createBlogDirectory(id, mt, st, interval) {
    //Get the blog post body div container
    var elem = document.getElementById(id);
    if (!elem) return false;
    //Get all element nodes in div
    var nodes = elem.getElementsByTagName("*");
    //Create a div container for the blog directory
    var divSideBar = document.createElement('DIV');
    divSideBar.className ='uprightsideBar';
    divSideBar.setAttribute('id','uprightsideBar');
    var divSideBarTab = document.createElement('DIV');
    divSideBarTab.setAttribute('id','sideBarTab');
    divSideBar.appendChild(divSideBarTab);
    var h2 = document.createElement('H4');
    divSideBarTab.appendChild(h2);
    var txt = document.createTextNode('directory navigation');
    h2.appendChild(txt);
    var divSideBarContents = document.createElement('DIV');
    divSideBarContents.style.display ='none';
    divSideBarContents.setAttribute('id','sideBarContents');
    divSideBar.appendChild(divSideBarContents);
    //Create a custom list
    var dlist = document.createElement("dl");
    divSideBarContents.appendChild(dlist);
    var num = 0;//Statistics found mt and st
    mt = mt.toUpperCase();//Convert to uppercase
    st = st.toUpperCase();//Convert to uppercase
    //Traverse all element nodes
    for (var i = 0; i <nodes.length; i++) {
        if (nodes[i].nodeName == mt || nodes[i].nodeName == st) {
            //Get the title text
            var nodetext = nodes[i].innerHTML.replace(/<\/?[^>]+>/g, "");//The content in innerHTML may have HTML tags, so use regular expressions to remove HTML tags
            nodetext = nodetext.replace(/ /ig, "");//Replace all
            nodetext = htmlDecode(nodetext);
            //Insert anchor
            nodes[i].setAttribute("id", "blogTitle" + num);
            var item;
            switch (nodes[i].nodeName) {
                case mt: //If the main title
                    item = document.createElement("dt");
                    break;
                case st: //If it is a subtitle
                    item = document.createElement("dd");
                    break;
            }
            //Create anchor link
            var itemtext = document.createTextNode(nodetext);
            item.appendChild(itemtext);
            item.setAttribute("name", num);
            item.onclick = function () {//Add mouse click trigger function
                var pos = getElementPosition(document.getElementById("blogTitle" + this.getAttribute("name")));
                if (!moveScrollBar(pos.top, interval)) return false;
            };
            //Add the custom table item to the custom list
            dlist.appendChild(item);
            num++;
        }
    }
    if (num == 0) return false;
    /*Event handling when mouse enters*/
    divSideBarTab.onmouseenter = function () {
        divSideBarContents.style.display ='block';
    }
    /*Event handling when the mouse leaves*/
    divSideBar.onmouseleave = function () {
        divSideBarContents.style.display ='none';
    }

    document.body.appendChild(divSideBar);
};

function htmlDecode(text) {
    var temp = document.createElement("div");
    temp.innerHTML = text;
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
};

function getElementPosition(ele) {
    var topPosition = 0;
    var leftPosition = 0;
    while (ele) {
        topPosition += ele.offsetTop;
        leftPosition += ele.offsetLeft;
        ele = ele.offsetParent;
    }
    return {top: topPosition, left: leftPosition};
};

/*
    Move the scroll bar, finalPos is the destination position, internal is the moving speed
*/
function moveScrollBar(finalpos, interval) {
    //If this method is not supported, exit
    if (!window.scrollTo) {
        return false;
    }
    //When the form is scrolling, disable the mouse wheel
    window.onmousewheel = function () {
        return false;
    };
    //Clear timing
    if (document.body.movement) {
        clearTimeout(document.body.movement);
    }

    var currentpos = getScrollBarPosition();//Get the current position of the scroll bar

    var dist = 0;
    if (currentpos == finalpos) {//Return to the predetermined position, release the mouse wheel, and exit
        window.onmousewheel = function () {
            return true;
        }
        return true;
    }
    if (currentpos <finalpos) {//Not reached, then calculate the distance to be moved in the next step
        dist = Math.ceil((finalpos-currentpos) / 10);
        currentpos += dist;
    }
    if (currentpos> finalpos) {
        dist = Math.ceil((currentpos-finalpos) / 10);
        currentpos -= dist;
    }
    var scrTop = getScrollBarPosition();//Get the current position of the scroll bar
    window.scrollTo(0, currentpos);//Move the window
    if (getScrollBarPosition() == scrTop)//If it has reached the bottom, unlock the mouse wheel and exit
    {
        window.onmousewheel = function () {
            return true;
        }
        return true;
    }
    //Make the next move
    var repeat = "moveScrollBar(" + finalpos + "," + interval + ")";
    document.body.movement = setTimeout(repeat, interval);
};

/**
 * Get the current position of the scroll bar
 * @returns {number}
 */
function getScrollBarPosition() {
    var scrollBarPosition = document.body.scrollTop || document.documentElement.scrollTop;
    return scrollBarPosition;
};