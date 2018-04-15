package concessionaria.excelsior.com.br.concessionariaexcelsior

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        carregarSplash()
    }

    fun carregarSplash(){
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        ivSplashLogo.startAnimation(animacao)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }, 6000)
    }
}