//
//  ContentView.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 26.02.23.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject private var viewModel = ViewModel()
    
    @State private var question: String = ""
    @State private var isTyping = false
    
    struct RequestDto: Encodable {
        let prompt: String
        let max_tokens: Int
        let model: String
    }
    
    struct ResponseDto: Decodable {
        let text: String
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            HStack {
                InputText(text: $question, disabled: isTyping ^ viewModel.isLoading, onTap: viewModel.reset)
                SubmitButton(disabled: isTyping ^ viewModel.isLoading, action: sendRequest)
            }
            
            TypingText(fullText: viewModel.answer, isTyping: $isTyping)
            Spacer()
        }.padding()
    }
    
    func sendRequest() {
        Task {
            try await viewModel.sendRequest(question: question)
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
