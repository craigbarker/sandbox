package org.sgodden.tom.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.{Map => JavaMap}

@Component
class StateManager {
  @Autowired
  private var stateObjects: JavaMap[String, CustomerOrderState] = null
}