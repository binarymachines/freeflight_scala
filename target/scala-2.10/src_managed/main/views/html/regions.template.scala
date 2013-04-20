
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object regions extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("FreeFlight regions")/*3.28*/ {_display_(Seq[Any](format.raw/*3.30*/("""
    
    Hello from the Region controller!
    
""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.Html = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Apr 20 16:36:49 EDT 2013
                    SOURCE: /Users/dtaylor/workshop/scala/freeflight/app/views/regions.scala.html
                    HASH: bd358b73eab310168ee4b18bfbb34de08edf160c
                    MATRIX: 507->1|601->18|638->21|672->47|711->49|791->99
                    LINES: 19->1|22->1|24->3|24->3|24->3|28->7
                    -- GENERATED --
                */
            