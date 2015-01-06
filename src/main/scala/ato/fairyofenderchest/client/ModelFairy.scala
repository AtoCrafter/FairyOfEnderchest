package ato.fairyofenderchest.client

import net.minecraft.client.model.{ModelBase, ModelRenderer}
import net.minecraft.entity.Entity

class ModelFairy extends ModelBase {

  // モデルの直方体を代入する変数
  var bottom: ModelRenderer = _
  var base: ModelRenderer = _
  var top: ModelRenderer = _

  // テクスチャの縦と横のサイズ
  textureWidth = 32
  textureHeight = 64
  // モデルの形を作る
  base = new ModelRenderer(this, 0, 14)
  base.addBox(0F, 0F, 0F, 10, 8, 10)

  bottom = new ModelRenderer(this, 32, 2)
  bottom.addBox(1F, 8F, 1F, 8, 8, 8)

  top = new ModelRenderer(this, 40, 18)
  top.addBox(2F, 16F, 2F, 6, 8, 6)

  override def render(entity: Entity, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
    // 描画
    base.render(f5)
    bottom.render(f5)
    top.render(f5)
  }

}
