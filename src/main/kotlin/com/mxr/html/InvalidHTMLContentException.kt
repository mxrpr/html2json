package com.mxr.html

class InvalidHTMLContentException(val problematicElement: String) : Exception("Invalid HTML content: $problematicElement")