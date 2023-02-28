//
//  ContentView+Model.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 27.02.23.
//

import Foundation

extension ContentView {
    
    class ViewModel: ObservableObject {
        
        @Published private(set) var answer: String = " "
        @Published private(set) var isLoading: Bool = false
        
        func sendRequest(question: String) async throws {
            guard !question.isEmpty else { return }
            let requestDto = RequestDto(prompt: question, max_tokens: 100, model: "text-davinci-003")
            guard let payload = try? JSONEncoder().encode(requestDto) else { return }
            
            let url = URL(string: "https://api.openai.com/v1/completions")!
            
            var request = URLRequest(url: url)
            request.httpMethod = "POST"
            request.httpBody = payload
            request.setValue("Bearer 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", forHTTPHeaderField: "Authorization")
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            
            isLoading = true
            let (data, response) = try await URLSession.shared.data(for: request)
            
            guard (response as? HTTPURLResponse)?.statusCode == 200 else{
                DispatchQueue.main.async {
                    self.answer = "Unable to receive answer."
                    self.isLoading = false
                }
                return
            }
            
            let json = try? JSONSerialization.jsonObject(with: data, options: [])
            if let json = json as? [String: Any],
               let choices = json["choices"] as? [[String: Any]],
               let firstChoice = choices.first,
               let text = firstChoice["text"] as? String {
                DispatchQueue.main.async {
                    self.answer = text.replacingOccurrences(of: "^\\s*", with: "", options: .regularExpression)
                    self.isLoading = false
                }
            }
        }
        
        func reset() {
            answer = ""
            isLoading = false
        }
    }
}
