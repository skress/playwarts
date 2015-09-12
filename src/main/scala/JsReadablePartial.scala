package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{ WartTraverser, WartUniverse }

object JsReadablePartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val jsReadableSymbol = rootMirror.staticClass("play.api.libs.json.JsReadable")
    val AsName = TermName("as")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, AsName) if left.tpe.baseType(jsReadableSymbol) != NoType =>
            u.error(tree.pos, "JsReadable#as is disabled - use JsReadable#asOpt instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
