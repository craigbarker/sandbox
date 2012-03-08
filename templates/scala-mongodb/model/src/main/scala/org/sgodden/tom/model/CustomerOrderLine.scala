package org.sgodden.tom.model

import com.novus.salat.annotations.raw.Persist

case class CustomerOrderLine() {
  @Persist
  private var packageType: String = null
  @Persist
  private var descriptionOfGoods: String = null

  def getPackageType = packageType
  def setPackageType(packageType: String) { this.packageType = packageType }

  def getDescriptionOfGoods = descriptionOfGoods
  def setDescriptionOfGoods(descriptionOfGoods: String) = this.descriptionOfGoods = descriptionOfGoods
}
