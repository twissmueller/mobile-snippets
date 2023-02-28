//
//  TypingText.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 27.02.23.
//

import SwiftUI

struct TypingText: View {
    var fullText: String
    
    @State private var displayText: String = ""
    @State private var currentIndex: Int = 0
    
    @Binding var isTyping: Bool
    
    @State private var typingText: String = "" {
        didSet {
            displayText = ""
            currentIndex = 0
            
            guard !typingText.isEmpty else { return }
            
            isTyping = true
            
            let timer = Timer.scheduledTimer(withTimeInterval: 0.05, repeats: true) { timer in
                let index = typingText.index(typingText.startIndex, offsetBy: currentIndex)
                displayText += String(typingText[index])
                currentIndex += 1
                
                if currentIndex == typingText.count {
                    timer.invalidate()
                    isTyping = false
                }
            }
            
            RunLoop.current.add(timer, forMode: .common)
        }
    }
    
    var body: some View {
        VStack {
            Text(displayText)
                .onChange(of: fullText) { answer in
                    typingText = answer
                }
        }
    }
}
