package com.quickreach.sender

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class WhatsAppAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        val rootNode = rootInActiveWindow ?: return
        val pkg = event.packageName?.toString() ?: ""

        if (pkg == "com.whatsapp" || pkg == "com.whatsapp.w4b") {
            // Dismiss error dialog if number is not on WhatsApp
            val okButtons = rootNode.findAccessibilityNodeInfosByText("OK")
            for (btn in okButtons) {
                if (btn.isClickable) {
                    btn.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return
                }
            }

            // Click Send button automatically
            val sendById = rootNode.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send")
            if (sendById.isNotEmpty() && sendById[0].isEnabled) {
                sendById[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                return
            }

            val sendByText = rootNode.findAccessibilityNodeInfosByText("Send")
            for (node in sendByText) {
                if (node.isClickable) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return
                }
            }
        }
    }

    override fun onInterrupt() {}
}