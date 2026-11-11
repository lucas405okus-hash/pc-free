package com.cashpc.android

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private var imageUri: Uri? = null
    private val pickImage = 41

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 28)
            setBackgroundColor(0xFF10151D.toInt())
        }
        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        val title = TextView(this).apply {
            text = "CASH PC"
            textSize = 30f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = "Computador virtual experimental para Android"
            textSize = 14f
            setTextColor(0xFFB7C4D5.toInt())
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 28)
        }
        status = TextView(this).apply {
            text = "Motor de virtualização ainda não integrado. Esta versão valida a interface e a seleção segura de imagens."
            textSize = 15f
            setTextColor(0xFFFFD27A.toInt())
            setPadding(16, 18, 16, 18)
            setBackgroundColor(0xFF202A38.toInt())
        }
        content.addView(title)
        content.addView(subtitle)
        content.addView(status, match())
        content.addView(action("Selecionar imagem ISO") { openImagePicker() })
        content.addView(action("Iniciar máquina virtual") {
            status.text = if (imageUri == null) "Selecione uma imagem ISO primeiro. O motor QEMU ainda precisa ser integrado e validado antes de iniciar sistemas." else "Imagem selecionada. O motor QEMU ainda não está integrado nesta versão; nenhuma máquina foi iniciada."
        })
        content.addView(action("Limpar imagem") {
            imageUri = null
            status.text = "Imagem removida. Motor QEMU ainda não integrado."
        })
        val note = TextView(this).apply {
            text = "Compatibilidade prevista: Android ARM64. Sistemas x86 podem ficar muito lentos em celulares ARM. Use somente imagens de sistemas que você tenha direito de utilizar. O aplicativo não inclui Windows."
            textSize = 13f
            setTextColor(0xFF9EADBF.toInt())
            setPadding(4, 26, 4, 12)
        }
        content.addView(note)
        scroll.addView(content)
        root.addView(scroll, ViewGroup.LayoutParams(-1, -1))
        setContentView(root)
    }

    private fun match() = LinearLayout.LayoutParams(-1, -2)

    private fun action(label: String, onTap: () -> Unit): Button = Button(this).apply {
        text = label
        isAllCaps = false
        textSize = 15f
        setOnClickListener { onTap() }
        val p = LinearLayout.LayoutParams(-1, -2)
        p.topMargin = 14
        layoutParams = p
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/octet-stream"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/octet-stream", "application/x-iso9660-image", "application/x-cd-image"))
        }
        startActivityForResult(intent, pickImage)
    }

    @Deprecated("Deprecated in Android API; retained for broad device compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == RESULT_OK) {
            imageUri = data?.data
            imageUri?.let { uri ->
                contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                status.text = "Imagem selecionada: ${uri.lastPathSegment ?: "arquivo ISO"}. O motor QEMU ainda não está integrado; nada foi iniciado."
            }
        }
    }
}
