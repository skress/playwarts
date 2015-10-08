import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.JsReadablePartial
import org.scalatest.FunSuite
import play.api.libs.json.{JsUndefined, JsReadable}

class JsReadablePartialTest extends FunSuite {

  val jsReadable: JsReadable = new JsUndefined("")

  test("can't call JsReadable#as") {
    val result = WartTestTraverser(JsReadablePartial) {
      val foo = jsReadable.as[String]
    }
    assertResult(List("JsReadable#as is disabled - use JsReadable#asOpt instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("JsReadablePartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(JsReadablePartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.JsReadablePartial"))
      val foo = jsReadable.as[String]
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
