# codegen-parent
## api
## web
编辑器
```vue
<MonacoEditor
    theme="vs"
    :options="{colorDecorators: true,lineHeight: 24,tabSize: 2, language: 'java', theme: 'vs-dark'}"
    language="javascript"
    width="100%"
    height="100%"
    v-model:value="codeContent">
</MonacoEditor>
```